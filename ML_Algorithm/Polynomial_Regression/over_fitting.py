'''
Created on 2018年11月19日

@author: qiguangqin
'''

import numpy as np

import matplotlib.pyplot as plt

from sklearn.linear_model import LinearRegression

from test03.metrics import get_mse

from sklearn.preprocessing import StandardScaler

from sklearn.pipeline import Pipeline

from sklearn.preprocessing import PolynomialFeatures

from sklearn.model_selection import train_test_split

def get_data():
    
    np.random.seed(666)
    
    x=np.random.uniform(-3,3,size=100)
    
    X=x.reshape(-1,1)
    
    y=0.5*x**2+x+2+np.random.normal(0,1,size=100)
    
    return X,y



def under_fitting():
    
    '''
    使用线性回归，r2score 太小了
    '''
    
    X,y=get_data()
    
    X_train,X_test,y_train,y_test=get_train_test_split(X, y)
    
    lin_reg=LinearRegression()
    
    lin_reg.fit(X_train,y_train)
    
    y_predict=lin_reg.predict(X_train)
    
    print("MSE=",get_mse(y_train,y_predict))
    
    plt.scatter(X_train[:,0],y_train,alpha=0.5)
    
    plt.plot(X_train[:,0],y_predict,color="m")
    
    #score=lin_reg.score(X,y) # X_test y_test  r2score 
    
    '''
    
多项式  r2score  系数是不相同的， 使用RMSE,使用R2SCORE 没有问题
    '''
    
    #print(score)
    
    plt.show()
    
    
def get_train_test_split(X,y):
    
    
    X_train,X_test,y_train,y_test=train_test_split(X, y)
    
    return X_train,X_test,y_train,y_test

    
def poly_regression_repre(degree):
    
    X,y=get_data()
    
    X_train,X_test,y_train,y_test=get_train_test_split(X, y)
    
    poly_reg=Pipeline([("poly",PolynomialFeatures(degree=degree)),\
                       
           ("stand_scaler",StandardScaler()),\
           
           ("lin_reg",LinearRegression())])
    
    
    poly_reg.fit(X_train, y_train)
    
    y_predict_train=poly_reg.predict(X_train)
    
    y_predict_test=poly_reg.predict(X_test)
    
    print("test MSE=",get_mse(y_test, y_predict_test))  ## 过拟合后，泛型不好，所以要使用train_test  
    
    print("train MSE=",get_mse(y_train,y_predict_train)) ## train_mse 拟合出来，多项式 幂次越高，锯齿越多，mse 越小，但是泛型不好
        
    plt.scatter(X_train[:,0],y_train,alpha=0.5)
    
    plt.plot(np.sort(X_train[:,0]),y_predict_train[np.argsort(X_train[:,0])],alpha=0.7,color='m')
    
    plt.show()
    
    
def main():
    
    
    degree=2
    
    #under_fitting()
    
    poly_regression_repre(degree)

if __name__ == '__main__':
    
    main()