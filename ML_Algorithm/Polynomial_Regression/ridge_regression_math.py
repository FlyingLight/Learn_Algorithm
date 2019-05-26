'''
Created on 2019年2月6日

@author: qiguangqin
'''

import numpy as np

from test03.metrics import get_mse

from sklearn.model_selection import train_test_split

import matplotlib.pyplot as plt

from sklearn.preprocessing import PolynomialFeatures

np.random.seed(666)

def get_data():
    
    x=np.random.uniform(-3,3,size=100)
    
    X=x.reshape(-1,1)
    
    y=0.5*x**2+3+np.random.normal(0,1,size=100)
    
    return X,y


def get_train_test(X,y):
    
    X_train,X_test,y_train,y_test=train_test_split(X,y,test_size=0.2,random_state=666)
    
    return X_train,X_test,y_train,y_test


def get_poly(degree,X):
    
    poly=PolynomialFeatures(degree)
    
    poly.fit(X)
    
    poly_X=poly.transform(X)
    
    return poly_X

def plot_model(model,X,y,n):
    
    X_plot=np.linspace(-3,3,100).reshape(100,1)
    
    X_plot_poly=get_poly(n-1, X_plot)
    
    y_plot=model.predict(X_plot_poly)
    
    plt.scatter(X[:,0],y,alpha=0.5)
    
    #plt.scatter(X[:,0],y,alpha=0.5)
    
    plt.plot(X_plot[:,0],y_plot,alpha=0.8,color='m')
    
    plt.axis([-3,3,0,9])
    
    plt.show()

class ridge_regression:
    
    def __init__(self,alpha):
        
        self.alpha_=alpha
        
        self.coef_=None
        
        self.interception_=None
        
        self._theta=None
        
    
    def fit(self,X_train,y_train):
        
        
        assert X_train.shape[0]==len(y_train),"X_train and y_train must be same"
        
        #X_b=np.hstack([np.ones(shape=(len(y_train),1)),X_train])
        
        X_b=X_train
         
        n=X_b.shape[1]
        
        res=np.linalg.inv((X_b.T.dot(X_b)+np.eye(n)*self.alpha_))
        
        self._theta=res.dot((X_b.T.dot(y_train)))
        
        self.coef_=self._theta[1:]
        
        self.interception_=self._theta[0]
        
        return self
    
    
    def predict(self,X_predict):
        
        assert self._theta is not None
        
        #Xb_test=np.hstack([np.ones(shape=(len(X_predict),1)),X_predict])
       
        Xb_test=X_predict
        
        y_predict=Xb_test.dot(self._theta)
        
        return y_predict
        
    def score(self,X_predict,y_test):
        
        y_predict=self.predict(X_predict)
        
        score=get_mse(y_test, y_predict)
        
        return score
    

def main():
    
    X,y=get_data()
    
    X_train,X_test,y_train,y_test=get_train_test(X, y)
    
    degree=2  #10阶 x_test 数据集合，使用模型 预测结果差，产生过拟合
    
    alpha=0.01
    
    X_train_poly=get_poly(degree, X_train)
     
    X_test_poly=get_poly(degree, X_test)
    
    ridge_regression01=ridge_regression(alpha)
    
    ridge_regression01.fit(X_train_poly,y_train)
    
    score=ridge_regression01.score(X_test_poly,y_test)
    
    theta=ridge_regression01._theta
    
    n=len(theta)
    
    print("theta=",theta,"score=",score)
    
    #plt.scatter(X[:,0])
    
    plot_model(ridge_regression01, X, y,n)
    
    plt.show()
    
    #print(theta.shape,X_test_poly.shape)
       
if __name__ == '__main__':
    main()