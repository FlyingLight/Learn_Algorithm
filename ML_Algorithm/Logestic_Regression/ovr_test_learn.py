'''
Created on 2019年5月5日

@author: qiguangqin

'''
import numpy as np

#import math.factorial

import matplotlib.pyplot as plt

from collections import Counter

from sklearn.datasets import load_iris

from logestic_regression.logestic_reg_repre import Logestic_regression

from sklearn.model_selection import train_test_split

from test03.metrics import accuracy_score

def get_data():
    
    iris=load_iris()
    
    X=iris.data[:,:2]
    
    y=iris.target
    
    return X,y


def get_train_test(X,y):
    
    X_train,X_test,y_train,y_test=train_test_split(X,y,test_size=0.2,random_state=666)
    
    return X_train,X_test,y_train,y_test


def use_ovr_test(X_train,X_test,y_train,y_test):
    
    counter=Counter(y_train)
    
    y_test_pro=np.empty(shape=(len(counter),len(y_test))) ## matrix [threshold_num * sample_num] 

    for i,num in enumerate(list(counter)):

        y_train_num=np.array(y_train==num,dtype="float")
        
        log_reg=Logestic_regression()

        log_reg.fit(X_train,y_train_num)

        y_test_pro[i,:]=log_reg.predict_proba(X_test)

    y_test_predict=np.argmax(y_test_pro,axis=0)
    
    score=accuracy_score(y_test, y_test_predict)
    
    return y_test_predict,score,y_test


def use_ovo_test(X_train,X_test,y_train,y_test):
    
  pass


def main():
    
    X,y=get_data()
    
    print(list(enumerate(list(Counter(y)))))
    
   
    
    
    X_train,X_test,y_train,y_test=get_train_test(X, y)
    
    y_test_predict,score,y_test=use_ovr_test(X_train, X_test, y_train, y_test)
    
    plt.scatter(X[y==0,0],X[y==0,1],alpha=0.5)
    
    plt.scatter(X[y==1,0],X[y==1,1],alpha=0.5)
    
    plt.scatter(X[y==2,0],X[y==2,1],alpha=0.5)
    
    plt.show()
  
    print(y_test_predict,score,y_test)
    
if __name__ == '__main__':
    main()