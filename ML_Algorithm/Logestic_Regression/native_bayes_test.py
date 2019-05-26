'''
Created on 2019年4月22日

@author: qiguangqin
'''


from sklearn.datasets import make_moons

import numpy as np

from sklearn.naive_bayes import  GaussianNB

from sklearn.model_selection import train_test_split

from logestic_regression.logestic_reg_repre import plot_decision_boundary

import matplotlib.pyplot as plt

from sklearn.metrics import confusion_matrix,precision_score,recall_score

from sklearn.preprocessing import StandardScaler

from sklearn.pipeline import Pipeline

def get_data():
    
    
    X,y=make_moons(noise=0.15,random_state=666)
    
    return X,y


def na_bayes_gaus():
    
    return Pipeline([
        
        ("stand",StandardScaler()),
        
        ("guassian",GaussianNB())
        ])

def main():
    
    X,y=get_data()
    
    X_train,X_test,y_train,y_test=train_test_split(X,y,test_size=0.2,random_state=666)
    
    nb_clf=na_bayes_gaus()
    
    nb_clf.fit(X_train,y_train)
    
    y_pre=nb_clf.predict(X_test)
    
    plot_decision_boundary(nb_clf, axis=[-2,3,-1,1.5])
 
    cm=confusion_matrix(y_test, y_pre)
    
    ps=precision_score(y_test,y_pre)
    
    rs=recall_score(y_test,y_pre)
    
    score=nb_clf.score(X,y)
    
    print(cm,ps,rs,score)
    
    plt.scatter(X[y==0,0],X[y==0,1])
    
    plt.scatter(X[y==1,0],X[y==1,1])
       
    plt.show()

if __name__ == '__main__':
    main()