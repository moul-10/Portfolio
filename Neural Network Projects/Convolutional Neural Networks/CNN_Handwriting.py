import matplotlib.pyplot as plt
import numpy as np
import seaborn as sns
from sklearn.preprocessing import LabelBinarizer
from keras import datasets, layers, models, callbacks
from sklearn.model_selection import train_test_split
from sklearn.metrics import confusion_matrix

#load data
(train_images, train_labels), (test_images, test_labels) = datasets.mnist.load_data()

#show a few sample images with corresponding labels
fig, ax = plt.subplots(2, 3)
k=0
for i in range(2):
    for j in range (3):
        ax[i,j].imshow(train_images[k].reshape(28, 28), cmap='gray')
        ax[i, j].title.set_text(train_labels[k])
        k+=1
    plt.tight_layout()
plt.show()

#normalize pixel values
train_images, test_images = train_images/255, test_images/255

########################
# Input Data Reshaping #
########################

print('Before Reshaping:')
print('Train: X=%s, y=%s' %(train_images.shape, train_labels.shape))
print('Test: X=%s, y=%s' %(test_images.shape, test_labels.shape))
print()

train_images = train_images.reshape(-1, 28, 28, 1)
test_images = test_images.reshape(-1, 28, 28, 1)

lab_bin = LabelBinarizer()


train_labels = lab_bin.fit_transform(train_labels)
test_labels = lab_bin.fit_transform(test_labels)

print('After Reshaping:')
print('Train: X=%s, y=%s' %(train_images.shape, train_labels.shape))
print('Test: X=%s, y=%s' %(test_images.shape, test_labels.shape))
print()

#split test data and labels into train and test groups
X_trn, X_tst, y_trn, y_tst = train_test_split(train_images, train_labels, test_size=0.2, random_state=0)
#'''

######################
# Creating the Model #
######################

model = models.Sequential(
    [
         layers.Conv2D(filters=75, kernel_size=(3, 3), strides=1, padding='valid', activation='relu', input_shape=(28, 28, 1)),
         layers.MaxPool2D(pool_size=(2,2), strides=1, padding='valid'),
         layers.Conv2D(filters=50, kernel_size= (3, 3), strides=1, padding='valid', activation='relu'),
         layers.MaxPool2D(pool_size=(2,2), strides=1, padding='valid'),
         layers.Conv2D(filters=25, kernel_size= (3, 3), strides=1, padding='valid', activation='relu'),
         layers.MaxPool2D(pool_size=(2,2), strides=1, padding='valid'),
         layers.Flatten(),
         layers.Dense(units=64, activation='relu'),
         layers.Dense(units=10, activation='softmax')
     ]
)

# declaring utility functions to prevent overtraining
early_stopping = callbacks.EarlyStopping(monitor='val_loss', min_delta=0.001, patience=3, verbose=1)
red_lr = callbacks.ReduceLROnPlateau(monitor='val_loss', factor=0.5, patience=1, min_lr=0.00001, verbose=1)

#compile  model
model.compile(optimizer='adam', loss='categorical_crossentropy', metrics=['accuracy'])

#fit model
history = model.fit(X_trn, y_trn, batch_size=256, epochs=15, validation_data=(X_tst, y_tst), callbacks=[early_stopping, red_lr])

#print results: first accuracy then loss
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

# print accuracy and loss of TEST
test_loss, test_accuracy = model.evaluate(test_images, test_labels, verbose=1)

print('Accuracy: %s, Loss: %s' %(test_accuracy, test_loss))

# have model make predictions based on Test images
predictions = model.predict_classes(test_images)

# get rid of one-hot encoding to use in confusion matrix
test_labels = np.argmax(test_labels, axis=1)

cm = confusion_matrix(test_labels, predictions)

fig, ax = plt.subplots(figsize=(12,12))
sns.heatmap(cm, cmap='Reds', linewidth=0.5, linecolor='Black', annot=True, fmt="d")


#'''