'''
Created on 2018年11月21日

@author: qiguangqin
'''

import numpy as np

from sklearn.model_selection import train_test_split

from sklearn.preprocessing import PolynomialFeatures

from sklearn.linear_model import LinearRegression

from test03.metrics import get_mse

import matplotlib.pyplot as plt

from sklearn.pipeline import Pipeline

from sklearn.preprocessing import StandardScaler


'''
学习曲线
'''

def get_data():
    
    np.random.seed(666)
    
    x=np.random.uniform(-3.0,3.0,size=100)
    
    X=x.reshape(-1,1)
    
    y=0.5*x**2+x+2+np.random.normal(0,1,size=100)
    
    return X,y


def get_train_test(X,y):
    
    X_train,X_test,y_train,y_test=train_test_split(X,y)
    
    return X_train,X_test,y_train,y_test


def learn_curve_plot(algo,X_train,X_test,y_train,y_test):
    
    m=len(X_train)
    
    train_score=[]
    
    test_score=[] 
    
    for i in range(1,m+1):
        
        algo.fit(X_train[:i,:],y_train[:i])
        
        y_train_predict=algo.predict(X_train[:i])
        
        y_test_predict=algo.predict(X_test)
        
        train_score.append(get_mse(y_train[:i],y_train_predict))
        
        test_score.append(get_mse(y_test,y_test_predict))
        
    plt.plot([i for i in range(m)],np.array(np.sqrt(train_score)),alpha=0.5,label="train_score")
    
    plt.plot([i for i in range(m)],np.array(np.sqrt(test_score)),alpha=0.5,label="test_score")
    
    plt.axis([0,len(X_train),0,4])
    
    plt.legend()
    
    plt.show()
    
    
def plot_learn_linear_regression():
    
    X,y=get_data()
    
    lin_reg=LinearRegression()
    
    X_train,X_test,y_train,y_test=get_train_test(X, y)
    
    learn_curve_plot(lin_reg, X_train, X_test, y_train, y_test)
    
    
def plot_learn_poly_regression(degree):
    
    X,y=get_data()
    
    X_train,X_test,y_train,y_test=get_train_test(X, y)
    
    poly_reg=Pipeline([("poly",PolynomialFeatures(degree=degree)),\
                        ("stand_scaler",StandardScaler()),\
                        ("lin_reg",LinearRegression()),\
                      ])
    
    learn_curve_plot(poly_reg, X_train, X_test, y_train, y_test)
    
    
    
def main():
    
    plot_learn_linear_regression()
    
    degree=2
    
    plot_learn_poly_regression(degree)

if __name__ == '__main__':
    
    main()