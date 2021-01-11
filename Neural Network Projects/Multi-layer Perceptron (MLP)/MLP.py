import matplotlib.pyplot as plt
import pandas as pd
import seaborn as sns
from sklearn.model_selection import train_test_split
from sklearn.neural_network import MLPClassifier
from sklearn.metrics import accuracy_score, confusion_matrix

#to print full rows
pd.set_option("display.max_rows", None, "display.max_columns", None) 

#########################
# PARAMETERS FOR MODELS #
#########################
hidden_neurons = 50
epochs = 10000
learning_rate = 0.01
no_change_thresh = epochs

#########################
#     IRIS DATA SET     #
#########################
file = 'iris.csv'
df = pd.read_csv(file, header=None)
df.columns = ['s_length', 's_width', 'p_length', 'p_width', 'class']
df=df.replace({'Iris-setosa': 0, 'Iris-versicolor': 1, 'Iris-virginica': 2})
#1:Iris-setosa, 2:Iris-versicolor, 3:Iris-virginica
X = df.iloc[:, 0:4]
y = df['class']

#Split test data from training data, 80% Training, 20% Test
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, shuffle=True, random_state=8)


#print(y_train.describe()) #used to find a good randomstate value using median = 1 and mean = 0.991667
mlp_iris_1 = MLPClassifier(hidden_layer_sizes=(hidden_neurons,), activation= 'logistic', 
                    solver='sgd', learning_rate='constant', learning_rate_init=learning_rate, 
                    max_iter = epochs, random_state= 9, tol=1e-7, verbose=False, 
                    momentum= 0.9, n_iter_no_change=no_change_thresh)

mlp_iris_2 = MLPClassifier(hidden_layer_sizes=(hidden_neurons,), activation= 'tanh', 
                    solver='sgd', learning_rate='constant', learning_rate_init=learning_rate, 
                    max_iter = epochs, random_state= 9, tol=1e-7, verbose=False, 
                    momentum= 0.9, n_iter_no_change=no_change_thresh)

mlp_iris_1.fit(X_train, y_train)
mlp_iris_2.fit(X_train, y_train)

y_pred_1 = mlp_iris_1.predict(X_test)
acc_score_1 = accuracy_score(y_test, y_pred_1)
y_pred_2 = mlp_iris_2.predict(X_test)
acc_score_2 = accuracy_score(y_test, y_pred_2)
print("Accuracy Score (Sigmoid): ", acc_score_1)
print("Accuracy Score (TanH): ", acc_score_2)




cm1 = confusion_matrix(y_test, y_pred_1)
cm2 = confusion_matrix(y_test, y_pred_2)

heatmap=sns.heatmap(cm1, cmap="YlGnBu", annot=True,
                    xticklabels=['Iris-setosa', 'Iris-versicolor', 'Iris-virginica'], 
                    yticklabels=['    Iris-setosa', '    Iris-versicolor', '    Iris-virginica'])
plt.title("Confusion Matrix (Sigmoid)")
plt.ylabel("Actual Class")
plt.xlabel("Predicted Class")
plt.show()

heatmap=sns.heatmap(cm2, cmap="inferno", annot=True,
                    xticklabels=['Iris-setosa', 'Iris-versicolor', 'Iris-virginica'], 
                    yticklabels=['    Iris-setosa', '    Iris-versicolor', '    Iris-virginica'])
plt.title("Confusion Matrix (HTan)")
plt.ylabel("Actual Class")
plt.xlabel("Predicted Class")
plt.show()

fig, ax = plt.subplots()
ax.plot(mlp_iris_1.loss_curve_, label='Sigmoid')
ax.plot(mlp_iris_2.loss_curve_, label='Hyperbolic Tangent')
ax.legend()
plt.title("Iris Loss Curve")
plt.ylabel("Loss")
plt.xlabel("Number of Epochs")

#########################
#    SPIRAL DATA SET    #
#########################
file_spiral = 'spiral.csv'
df_spiral = pd.read_csv(file_spiral)
X = df_spiral.iloc[:, 0:2] #First 2 columns are inputs
y = df_spiral['Column3'] #result class
X_test = pd.read_csv('spiraltest.csv').iloc[:, 0:2]


mlp_spiral_1 = MLPClassifier(hidden_layer_sizes=(40, ), activation= 'tanh', 
                    solver='sgd', learning_rate='constant', learning_rate_init=0.1, 
                    max_iter = 50000, random_state= 0, tol=1e-9, verbose=False, 
                    momentum= 0.9, n_iter_no_change=epochs)

mlp_spiral_1.fit(X, y)

y_pred_spiral = mlp_spiral_1.predict(X_test)

#print(y_pred_spiral)


fig1, ax1 = plt.subplots()
#ax1.plot(mlp_spiral_1.loss_curve_)


plt.title("Spiral Graph")
sns.scatterplot(x=X_test['Column1'], y=X_test['Column2'], hue=y_pred_spiral)
#sns.scatterplot(X['Column1'], X['Column2'], hue=y) #Training Data Graph
plt.ylabel("Y")
plt.xlabel("X")
