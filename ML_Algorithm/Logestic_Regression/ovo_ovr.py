'''
Created on 2019年1月28日

@author: qiguangqin
'''

import numpy as np

import matplotlib.pyplot as plt

from sklearn.datasets import load_iris

from sklearn.model_selection import train_test_split

from sklearn.linear_model import LogisticRegression

from logestic_regression.logestic_reg_repre import plot_decision_boundary

from sklearn.multiclass import OneVsRestClassifier

from sklearn.multiclass import OneVsOneClassifier


def get_data():
    
    iris=load_iris()
    
    X=iris.data[:,:2]
    
    y=iris.target
    
    return X,y


def get_all_data():
    
    iris=load_iris()
    
    X=iris.data
    
    y=iris.target
    
    return X,y

def use_logestic_sk_ovr(X,y):

    log_reg=LogisticRegression()
    
    X_train,X_test,y_train,y_test=train_test_split(X,y,test_size=0.2,random_state=666)
    
    log_reg.fit(X_train,y_train)
    
    score=log_reg.score(X_test,y_test)
    
    print("score=",score)
    
    plot_decision_boundary(log_reg, axis=[4.0,8.5,1.5,4.5])
    
    plt.scatter(X[y==0,0],X[y==0,1],alpha=0.6)
    
    plt.scatter(X[y==1,0],X[y==1,1],alpha=0.6)
    
    plt.scatter(X[y==2,0],X[y==2,1],alpha=0.6)
    
    plt.show()
    
    
def use_logestic_sk_ovo(X,y):
    
    log_reg=LogisticRegression(multi_class="multinomial",solver="newton-cg")
    
    X_train,X_test,y_train,y_test=train_test_split(X,y,test_size=0.2,random_state=666)
    
    log_reg.fit(X_train,y_train)
    
    score=log_reg.score(X_test,y_test)
    
    print("score=",score)
    
    plot_decision_boundary(log_reg, axis=[4.0,8.5,1.5,4.5])
    
    plt.scatter(X[y==0,0],X[y==0,1],alpha=0.6)
    
    plt.scatter(X[y==1,0],X[y==1,1],alpha=0.6)
    
    plt.scatter(X[y==2,0],X[y==2,1],alpha=0.6)
    
    plt.show()
    

def use_logestic_ovo_all():
    
    X,y=get_all_data()
     
    log_reg=LogisticRegression(multi_class="multinomial",solver="newton-cg")
    
    X_train,X_test,y_train,y_test=train_test_split(X,y,test_size=0.2,random_state=666)
    
    log_reg.fit(X_train,y_train)
    
    score=log_reg.score(X_test,y_test)
    
    print("score=",score)
    
def use_class_ovo(X,y):
    
    log_reg=LogisticRegression()
    
    X_train,X_test,y_train,y_test=train_test_split(X,y,test_size=0.2,random_state=666)
   
    ovr=OneVsRestClassifier(log_reg)
    
    ovr.fit(X_train, y_train)
    
    score=ovr.score(X_test,y_test)
    
    print("score=",score)
    
def main():
    
    X,y=get_data()
    
   # use_logestic_sk_ovr(X, y)
    
    use_logestic_sk_ovo(X, y)
    
   # use_logestic_ovo_all()
    
   # use_class_ovo(X, y)

if __name__ == '__main__':
    
    main()