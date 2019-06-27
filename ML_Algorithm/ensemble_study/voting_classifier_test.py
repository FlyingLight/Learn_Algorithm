'''
Created on 2019年5月14日

@author: qiguangqin
'''

import numpy as np

#import matplotlib.pyplot as plt

from sklearn.datasets import make_moons

from sklearn.model_selection import train_test_split

from sklearn.tree import DecisionTreeClassifier

from sklearn.svm import SVC

from sklearn.linear_model import LogisticRegression

from sklearn.metrics import accuracy_score

from sklearn.ensemble import VotingClassifier


def get_data():
    
    X,y=make_moons(n_samples=500, noise=0.3, random_state=42)
    
    '''
    plt.scatter(X[y==0,0],X[y==0,1],alpha=0.5)
    
    plt.scatter(X[y==2,0],X[y==2,1],alpha=0.5,color="g")
    
    plt.scatter(X[y==1,0],X[y==1,1],alpha=0.5,color="r")
    
    plt.show()
    '''
    
    return X,y

def get_train_test(X,y):
    
    X_train,X_test,y_train,y_test=train_test_split(X,y,random_state=42)
    
    return X_train,X_test,y_train,y_test


def use_vote_classifier(X,y):
    
    X_train,X_test,y_train,y_test=get_train_test(X, y)
    
    log_clf=LogisticRegression()
    
    svm_clf=SVC()
    
    dt_clf=DecisionTreeClassifier()
    
    log_clf.fit(X_train,y_train)
    
    svm_clf.fit(X_train,y_train)
    
    dt_clf.fit(X_train,y_train)
    
    score1=log_clf.score(X_test,y_test)
    
    score2=svm_clf.score(X_test,y_test)
    
    score3=dt_clf.score(X_test,y_test)
    
    print("logestic_regression=",score1,"svm_regression=",score2,"decision_tree=",score3)
    
    y_predict1=log_clf.predict(X_test)
    
    y_predict2=svm_clf.predict(X_test)
    
    y_predict3=dt_clf.predict(X_test)
    
    y_predict=np.array((y_predict1+y_predict2+y_predict3)>=2,dtype="int")##　一共有多少个模型认为是１，如果是大于等于２

    score=accuracy_score(y_test, y_predict)
    
    print("score=",score,y_predict[:10])
    
    
    
def use_std_vote_clf(X,y):
    
    '''
    estimator 传入一个列表，列表里面是tuple
    
    tuple  ("name",model)
    '''
    X_train,X_test,y_train,y_test=get_train_test(X, y)
    
    voting_clf=VotingClassifier(estimators=[
        
        ("log_clf",LogisticRegression()),\
        
        ("svm_clf",SVC()),\
        
        ("dt_clf",DecisionTreeClassifier())
        ],voting="hard")   
    
    voting_clf.fit(X_train,y_train) 
    
    score=voting_clf.score(X_test,y_test)
    
    print("sklearn_pipe_voting_classifier_score=",score)
    
def main():

    X,y=get_data()
    
    use_vote_classifier(X, y)
    
    use_std_vote_clf(X, y)
    
    

if __name__ == '__main__':
    main()