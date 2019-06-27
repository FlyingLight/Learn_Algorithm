'''
Created on 2019年5月26日

@author: qiguangqin
'''

import numpy as np
import matplotlib.pyplot as plt
from sklearn.model_selection import train_test_split
from sklearn.datasets import make_moons
from sklearn.ensemble import BaggingClassifier
from sklearn.tree import DecisionTreeClassifier
from logestic_regression.logestic_reg_repre import plot_decision_boundary

def get_data():
    
    X,y=make_moons(n_samples=500, noise=0.2, random_state=42)

    return X,y
    
    plt.scatter(X[y==0,0],X[y==0,1],alpha=0.5)
    
    plt.scatter(X[y==1,0],X[y==1,1],alpha=0.5)
    
    plt.show()
    
def get_train_test(X,y):
    
    X_train,X_test,y_train,y_test=train_test_split(X,y,random_state=666)
    
    return X_train,X_test,y_train,y_test 
    
def sk_bagging_use():
    
    bagging_clf=BaggingClassifier(DecisionTreeClassifier(),n_estimators=500,max_samples=100,bootstrap=True)
    
    return bagging_clf
    
    ##n_estimator  要集成几个模型，进行判断，一共要有500个子模型
    
    ##max_sample 每一个子模型要看几个数据 ，看了100个数据
    
def sk_bagging_oob_use():
    
    bagging_clf=BaggingClassifier(DecisionTreeClassifier(),n_estimators=500,max_samples=100,bootstrap=True,oob_score=True)
    
    return bagging_clf
    
def main():
    
    X,y=get_data()
    
    X_train,X_test,y_train,y_test =get_train_test(X, y)
    
    bagging_clf=sk_bagging_use()
    
    bagging_clf.fit(X_train,y_train)
    
    #y_predict=bagging_clf.predict(X_test)
    
    score=bagging_clf.score(X_test, y_test)
    
    print("score=",score)
    
    plot_decision_boundary(bagging_clf,axis=[-2,3,-2,2])
    
    plt.scatter(X[y==0,0],X[y==0,1],alpha=0.5)
    
    plt.scatter(X[y==1,0],X[y==1,1],alpha=0.5)
    
    plt.show()
    
if __name__ == '__main__':
    main()