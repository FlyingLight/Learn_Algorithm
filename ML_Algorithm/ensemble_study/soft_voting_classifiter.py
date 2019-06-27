'''
Created on 2019年5月15日

@author: qiguangqin
'''

import numpy as np

import matplotlib.pyplot as plt

from sklearn.datasets import make_moons

from logestic_regression.logestic_reg_repre import Logestic_regression

from sklearn.linear_model import LogisticRegression

from test03.knn_test03 import knn_classifier

from sklearn.model_selection import train_test_split

from test03.metrics import accuracy_score

from sklearn.ensemble import VotingClassifier

from sklearn.svm.classes import SVC

from sklearn.tree import DecisionTreeClassifier


np.random.seed(100)

def get_data():
    
    X,y=make_moons(n_samples=500, noise=0.3, random_state=42)
    
    return X,y


def get_train_test(X,y):
    
    X_train,X_test,y_train,y_test=train_test_split(X,y)
    
    return X_train,X_test,y_train,y_test 



def use_soft_voting_classifier(X,y):
    
    log_reg_clf=Logestic_regression()
    
    X_train,X_test,y_train,y_test=get_train_test(X, y)
    
    log_reg_clf.fit(X_train, y_train)
    
    proba_log=log_reg_clf.predict_proba(X_test)
    
    '''
    -----------------------------
    '''
    k=5
    
    knn_clf=knn_classifier(k)
    
    knn_clf.fit(X_train, y_train)
    
    proba_clf=knn_clf.predict_proba(X_test)
    
    '''
    ----------------------------------
    '''
    
    proba_all=(proba_log+proba_clf)/2
    
    y_predict=np.array(proba_all>0.5,dtype="float")
    
    score=accuracy_score(y_test,y_predict)
    
    print(score)
    
def def_sk_soft_voting_classifier():
    
    return VotingClassifier(estimators=[
        
        ('log_clf',LogisticRegression()),
        ('svm_clf',SVC(probability=True)),
        ('dec_tre_clf',DecisionTreeClassifier(random_state=666))
        
        ],voting='soft')
    

def use_sk_voting_classifier(X,y):
    
    X_train,X_test,y_train,y_test=get_train_test(X, y)
    
    sk_sft_vt_clf=def_sk_soft_voting_classifier()
    
    sk_sft_vt_clf.fit(X_train,y_train)
    
    score=sk_sft_vt_clf.score(X_test,y_test)
    
    print("sk_sft_voting score=",score)

def main():
    
    X,y=get_data()
    
    use_soft_voting_classifier(X, y)
    
    use_sk_voting_classifier(X, y)
    
    #plt.scatter(X[y==0,0],X[y==0,1],alpha=0.5)
    
    #plt.scatter(X[y==1,0],X[y==1,1],alpha=0.5)
    
    #plt.show()

if __name__ == '__main__':
    main()