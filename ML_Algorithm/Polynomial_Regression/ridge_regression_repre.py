'''
Created on 2018年12月4日

@author: qiguangqin
'''

import numpy as np

from sklearn.pipeline import Pipeline

from sklearn.preprocessing import PolynomialFeatures

from sklearn.preprocessing import StandardScaler

from sklearn.linear_model import LinearRegression

from sklearn.linear_model import Ridge

from sklearn.model_selection import train_test_split

from sklearn.metrics import mean_squared_error

import matplotlib.pyplot as plt

np.random.seed(42)

def get_data():
    
    x=np.random.uniform(-3,3,size=100)
    
    X=x.reshape(-1,1)
    
    y=0.5*x**2+3+np.random.normal(0,1,size=100)
    
    return X,y


def get_poly_simple_regression(degree):
    
    return Pipeline([\
        
        ("poly_reg",PolynomialFeatures(degree=degree)),\
        
        ("stand_scaler",StandardScaler()),\
        
        ("linear_regression",LinearRegression())
        
        ])


def ridge_regression(degree,alpha):##封装了岭回归模型
    
    return Pipeline([\
        
        ("poly",PolynomialFeatures(degree=degree)),\
        
        ("stand_scaler",StandardScaler()),\
        
        ("ridge_regression",Ridge(alpha=alpha))
        ])
    
def get_train_test(X,y):
    
    
    X_train,X_test,y_train,y_test=train_test_split(X,y,test_size=0.2,random_state=666)
    
    return X_train,X_test,y_train,y_test


def plot_model(model,X,y):
    
    X_plot=np.linspace(-3,3,100).reshape(100,1)
    
    y_plot=model.predict(X_plot)
    
    plt.scatter(X[:,0],y,alpha=0.5)
    
    plt.plot(X_plot[:,0],y_plot,alpha=0.8,color='m')
    
    plt.axis([-3,3,0,9])
    
    plt.show()
    
def simple_poly():
    
    X,y=get_data()
    
    X_train,X_test,y_train,y_test=get_train_test(X, y)
    
    degree=2  # 20阶 x_test 数据集合，使用模型 预测结果差，产生过拟合
    
    poly_reg=get_poly_simple_regression(degree)
    
    poly_reg.fit(X_train,y_train)
    
    plot_model(poly_reg, X, y)
    
    y_predict_test=poly_reg.predict(X_test)
    
    y_predict_train=poly_reg.predict(X_train)
    
    mse_train=mean_squared_error(y_train,y_predict_train)
    
    mse_test=mean_squared_error(y_test,y_predict_test)
    
    print("mse_test=",mse_test,"mse_train=",mse_train)
    

def ridge_poly(degree,alpha):
    
    X,y=get_data()
    
    X_train,X_test,y_train,y_test=get_train_test(X, y)
     
    ridge_reg=ridge_regression(degree,alpha) ## theta 的值一般比较大，所以需要将alpha的值放小一些
    
    ridge_reg.fit(X_train,y_train)
    
    y_predict_test=ridge_reg.predict(X_test)
    
    mse=mean_squared_error(y_test,y_predict_test)
    
    print("test_mse_ridge_regression=",mse)
    
    plot_model(ridge_reg, X, y)
    
def main():
    
    #simple_poly()
    
    alpha=1 ##alpha 越大，theta_i 越小，越是接近一条直线
    
    degree=20
    
    ridge_poly(degree, alpha)

if __name__ == '__main__':
    
    main()