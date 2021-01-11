import matplotlib.pyplot as plt
import numpy as np
import pandas as pd
import seaborn as sns
from sklearn.preprocessing import LabelBinarizer
from sklearn.metrics import confusion_matrix
from keras import layers, models, callbacks
from sklearn.model_selection import train_test_split

#############
# Load Data #
#############

train_df = pd.read_csv('sign_mnist_train.csv')
test_df = pd.read_csv('sign_mnist_test.csv')

y_train = train_df['label']
y_test =  test_df['label']
X_train = train_df.drop(['label'], axis=1)
X_test = test_df.drop(['label'], axis=1)
del train_df
del test_df

######################
# Show Sample Images #
######################

fig, ax = plt.subplots(2, 3)
k=0
for i in range(2):
    for j in range (3):
        ax[i,j].imshow(X_train.iloc[k].values.reshape(28,28), cmap='gray')
        ax[i, j].title.set_text(chr(y_train[k]+65))
        k+=1
    plt.tight_layout()
plt.show()

#'''
#Normalize pixel values to be between 0 and 1
X_train, X_test = X_train/255.0, X_test/255.0

########################
# Input Data Reshaping #
########################

print('Before Reshaping:')
print('Train: X=%s, y=%s' %(X_train.shape, y_train.shape))
print('Test: X=%s, y=%s' %(X_test.shape, y_test.shape))
print()

lab_bin = LabelBinarizer()

y_train = lab_bin.fit_transform(y_train)
y_test = lab_bin.fit_transform(y_test)

X_train = X_train.values.reshape(-1, 28, 28, 1)
X_test = X_test.values.reshape(-1, 28, 28, 1)

print('After Reshaping:')
print('Train: X=%s, y=%s' %(X_train.shape, y_train.shape))
print('Test: X=%s, y=%s' %(X_test.shape, y_test.shape))
print()

# Split training and testing data
X_trn, X_tst, y_trn, y_tst = train_test_split(X_train, y_train, test_size=0.2, random_state=0)


######################
# Creating the Model #
######################

model = models.Sequential()

model.add(layers.Conv2D(filters=75, kernel_size=(3, 3), strides=1, padding='valid', activation='relu', input_shape=(28, 28, 1)))
model.add(layers.MaxPool2D(pool_size=(2,2), strides=1, padding='valid'))
model.add(layers.Conv2D(filters=50, kernel_size= (3, 3), strides=1, padding='valid', activation='relu'))
model.add(layers.MaxPool2D(pool_size=(2,2), strides=1, padding='valid'))
model.add(layers.Conv2D(filters=25, kernel_size= (3, 3), strides=1, padding='valid', activation='relu'))
model.add(layers.MaxPool2D(pool_size=(2,2), strides=1, padding='valid'))
model.add(layers.Flatten())
model.add(layers.Dense(units=64, activation='relu'))
model.add(layers.Dense(units=24, activation='softmax'))

#specify utility functions for early stopping
#and learning rate slowing
early_stopping = callbacks.EarlyStopping(monitor='val_loss', min_delta=0.001, patience=3, verbose=1)
red_lr = callbacks.ReduceLROnPlateau(monitor='val_loss', factor=0.0002, patience=1, min_lr=0.00001, verbose=1)

#compile model
model.compile(optimizer='adam', loss='categorical_crossentropy', metrics=['accuracy'])

#train model
history = model.fit(X_trn, y_trn, batch_size=256, epochs=15, validation_data=(X_tst, y_tst), callbacks=[early_stopping, red_lr])


#################################
# Plot Accuracy and Loss Curves #
#################################

plt.plot(history.history['accuracy'], label='Accuracy')
plt.plot(history.history['val_accuracy'], label='Validation Accuracy')
plt.title('Training Accuracy and Validation')
plt.xlabel('Epoch')
plt.ylabel('Accuracy')
plt.legend(loc='lower right')
plt.show()

plt.plot(history.history['loss'], label='Loss')
plt.plot(history.history['val_loss'], label='Validation Loss')
plt.title('Training Loss and Validation')
plt.xlabel('Epoch')
plt.ylabel('Loss')
plt.legend(loc='upper right')
plt.show()

#show test accuracy
test_loss, test_accuracy = model.evaluate(X_test, y_test, verbose=1)

print('Accuracy: %s, Loss: %s' %(test_accuracy, test_loss))

# get predictions from model
predictions = model.predict_classes(X_test)

#convert test labels from one-hot encoding to use in CM
y_test = np.argmax(y_test, axis=1)

#plot CM and heatmap
cm = confusion_matrix(y_test, predictions)

fig, ax = plt.subplots(figsize=(24,24))
sns.heatmap(cm, cmap='Blues', linewidth=0.5, linecolor='Black', annot=True, fmt="d")

#'''