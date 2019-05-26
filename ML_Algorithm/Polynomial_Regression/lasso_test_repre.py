'''
Created on 2018年12月5日

@author: qiguangqin
'''

import numpy as np

from sklearn.pipeline import Pipeline

from sklearn.preprocessing import PolynomialFeatures

from sklearn.preprocessing import StandardScaler

from sklearn.linear_model import Lasso

from sklearn.model_selection import train_test_split

from sklearn.metrics import mean_squared_error

import matplotlib.pyplot as plt

np.random.seed(42)

def get_data():
    
    x=np.random.uniform(-3,3,size=100)
    
    X=x.reshape(-1,1)
    
    y=0.5*x**2+3+np.random.normal(0,1,size=100)
    
    return X,y

def get_simple_lasso_regression(degree,alpha):
    
    
    return Pipeline([
        
        ("poly_reg",PolynomialFeatures(degree=degree)),\
        
        ("stand_scaler",StandardScaler()),\
        
        ("lasso_regression",Lasso(alpha=alpha))
        
        ])
    
def plot_model(model,X,y):
    
    X_plot=np.linspace(-3,3,100).reshape(100,1)
    
    y_plot=model.predict(X_plot)
    
    plt.scatter(X[:,0],y,alpha=0.5)
    
    plt.plot(X_plot[:,0],y_plot,alpha=0.8,color='m')
    
    plt.axis([-3,3,0,9])
    
    plt.show()
    
def lasso_poly():
    
    X,y=get_data()
    
    X_train,X_test,y_train,y_test=train_test_split(X,y,test_size=0.2,random_state=666)
    
    degree=20
    
    alpha=0.01  ##ａ
    
    lasso_reg=get_simple_lasso_regression(degree, alpha) #alpha 0.01 可以比岭回归大一些
    
    lasso_reg.fit(X_train,y_train)
    
    y_predict_test=lasso_reg.predict(X_test)
    
    mse=mean_squared_error(y_test,y_predict_test)
    
    print("lasso_mse=",mse)
    
    plot_model(lasso_reg, X, y)
    
def main():

    lasso_poly()
    
if __name__ == '__main__':
    
    main()
    