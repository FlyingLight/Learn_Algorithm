'''
Created on 2019年3月3日

@author: qiguangqin
'''

import numpy as np

import matplotlib.pyplot as plt

from sklearn.datasets import load_iris

from sklearn.tree import DecisionTreeClassifier

from logestic_regression.logestic_reg_repre import plot_decision_boundary

def get_data():
    
    iris=load_iris()
    
    X=iris.data[:,2:]
    
    y=iris.target
    
    return X,y


def use_simple_decision_tree():
    
    X,y=get_data()
    
    dt_clf=DecisionTreeClassifier(max_depth=2,criterion="entropy")
    
    dt_clf.fit(X,y)
    
    plot_decision_boundary(dt_clf, axis=[0.5,7.5,0,3])
    
    plt.scatter(X[y==0,0],X[y==0,1],alpha=0.5)
    
    plt.scatter(X[y==1,0],X[y==1,1],alpha=0.5)
    
    plt.scatter(X[y==2,0],X[y==2,1],alpha=0.5)
    
    plt.show()
    
def ues_test_error_dct_tree():

    X,y=get_data()
    
    dt_clf=DecisionTreeClassifier(max_depth=2,criterion="entropy")
    
    X_new=np.delete(X,[138,34,2],axis=0)
    
    y_new=np.delete(y,[138,34,2])
    
    dt_clf.fit(X_new,y_new)
    
    #dt_clf.fit(X,y)
    
    plot_decision_boundary(dt_clf, axis=[0.5,7.5,0,3])
    
    plt.scatter(X[y==0,0],X[y==0,1],alpha=0.5)
    
    plt.scatter(X[y==2,0],X[y==2,1],alpha=0.5)
    
    plt.scatter(X[y==1,0],X[y==1,1],alpha=0.5)
    
    plt.show()

def main():
    
    #use_simple_decision_tree()
    ues_test_error_dct_tree()

if __name__ == '__main__':
    main()