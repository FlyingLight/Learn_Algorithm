#coding=utf-8
'''
Created on 2018年9月13日

@author: qiguangqin
'''

import numpy as np

import matplotlib.pyplot as plt

from test03.metrics import r2_score


class simple_linear_regession:
    
    "repre_ the use of simple linear regression"
    
    def __init__(self):
        
        " a_ and b_ are interal variables"
        
        self.a_=None
        
        self.b_=None
        
        
    def fit(self,x_train,y_train):
        
        " calculate a_and b_ from fit module"
        
        assert x_train.ndim==1, "x_train 1 dimension vector"
        
        assert len(x_train)==len(y_train),\
        "the Dimension of x_train and y_train must be same"
        
        x_train_mean_=np.mean(x_train)
        
        y_train_mean_=np.mean(y_train)
        
        numer=np.empty(shape=x_train.shape,dtype=float) # empty vector,shape like numer and dum shape
        
        dum=np.empty(shape=y_train.shape,dtype=float)
        
        for i in range(x_train.shape[0]):
              
            numer[i]=(x_train[i]-x_train_mean_)*(y_train[i]-y_train_mean_) ##
            
            dum[i]=(x_train[i]-x_train_mean_)*(x_train[i]-x_train_mean_)
                
        self.a_=np.sum(numer)/np.sum(dum)
        
        self.b_=y_train_mean_-self.a_*x_train_mean_
        
        return self

    
    def predict(self,X_test):
        
        assert X_test.ndim==1,"the X_test must be a vector"
        
        assert self.a_ is not None and self.b_ is not None,"a_ and b_ 不能为空"
        
        y_hat=[self._predict(x_single) for x_single in X_test]
        
        return y_hat
    
    
    def _predict(self,x_single):
           
        return self.a_*x_single+self.b_
    
    
class simple_linear_regression02:
    
    "repre_ the use of simple linear regression using vector"
    
    def __init__(self):
        
        " a_ and b_ are interal variables"
        
        self.a_=None
        
        self.b_=None
    
    def fit(self,x_train,y_train):
        
        "calculate a_and b_ from fit module"
        
        assert x_train.ndim==1, "x_train 1 dimension vector"
        
        assert len(x_train)==len(y_train),\
        "the Dimension of x_train and y_train must be same"
        
        x_train_mean_=np.mean(x_train)
        
        y_train_mean_=np.mean(y_train)
        
        self.a_=np.dot((x_train-x_train_mean_),(y_train-y_train_mean_))/np.dot((x_train-x_train_mean_),(x_train-x_train_mean_))
        
        self.b_=y_train_mean_-self.a_*x_train_mean_
        
        return self
    
    
    def predict(self,X_test):
       
        assert X_test.ndim==1,"the X_test must be a vector"
        
        assert self.a_ is not None and self.b_ is not None,"a_ and b_ 闈炵┖"
        
        y_hat=self.a_*X_test+self.b_
        
        return y_hat
    
    
    def score(self,X_test,y_test):  ## 
        
        
        y_predict=self.predict(X_test)
        
        r2_score11=r2_score(y_test, y_predict)
        
        return r2_score11
        
        
    
def main():


    x_train=np.array([1.,2.,3.,4.,5.])  ## x_train 鏄竴涓悜閲�
    
    y_train=np.array([1.,3.,2.,3.,5.])
    
    smp_lin_repre=simple_linear_regression02()
    
    smp_lin_repre.fit(x_train, y_train) 
    
    y_hat=smp_lin_repre.predict(x_train)
    
    print(smp_lin_repre.a_,smp_lin_repre.b_)
   
    plt.scatter(x_train,y_train,alpha=0.6,marker='o')
    
    plt.plot(x_train,y_hat,alpha=0.7,color='r')
    
    plt.axis([0.5,5.5,0,5.5])
    
    plt.xlabel("x")
    
    plt.ylabel("y")
     
    plt.show()

if __name__ == '__main__':
    
    main()

