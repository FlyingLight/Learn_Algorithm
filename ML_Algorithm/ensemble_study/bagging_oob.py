'''
Created on 2019年6月8日

@author: qiguangqin
'''

import numpy as np
import matplotlib.pyplot as plt
from sklearn.datasets import make_moons
from sklearn.tree import DecisionTreeClassifier
from sklearn.ensemble import BaggingClassifier

def get_data():
    
    X,y=make_moons(n_samples=500, noise=0.3, random_state=42)
    
    '''
    plt.scatter(X[y==0,0],X[y==0,1],alpha=0.5)
    
    plt.scatter(X[y==2,0],X[y==2,1],alpha=0.5,color="g")
    
    plt.scatter(X[y==1,0],X[y==1,1],alpha=0.5,color="r")
    
    plt.show()
    '''
    
    return X,y

def use_std_oob():
    
    bagging_clf=BaggingClassifier(DecisionTreeClassifier(),\
                                  n_estimators=500,\
                                  max_samples=100,bootstrap=True,oob_score=True,n_jobs=-1) ## 在放回取样的时候记录取哪些样本，才能知道哪些样本没有被取到
    
    return bagging_clf


def use_random_subspaces_clf():
    
    bagging_clf=BaggingClassifier(DecisionTreeClassifier(),\
                                  n_estimators=500,\
                                  max_samples=500,bootstrap=True,\
                                  oob_score=True,n_jobs=-1,\
                                  max_features=1,bootstrap_features=True) ## 每一次只是看一个特征上的随机样本，max_samples==n_estimators
    
    return bagging_clf

def use_random_patches_clf():
    
    bagging_clf=BaggingClassifier(DecisionTreeClassifier(),\
                                  n_estimators=500,\
                                  max_samples=100,bootstrap=True,\
                                  oob_score=True,n_jobs=-1,\
                                  max_features=1,bootstrap_features=True) ## 每一次只是看一个特征上的随机样本，max_samples==n_estimators
    
    return bagging_clf

def main():
    
    X,y=get_data()
    
    #bagging_clf=use_std_oob() ##　得到哪些样本没有取到
    
    #bagging_clf=use_random_subspaces_clf()
    
    bagging_clf=use_random_patches_clf()
    
    bagging_clf.fit(X,y)
    
    print(bagging_clf.oob_score_)

if __name__ == '__main__':
    main()