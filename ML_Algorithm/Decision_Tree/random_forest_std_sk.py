'''
Created on 2019年6月12日

@author: qiguangqin
'''

import numpy as np

import matplotlib.pyplot as plt

from sklearn.datasets import make_moons

from sklearn.ensemble import RandomForestClassifier,ExtraTreesClassifier

from sklearn.model_selection import GridSearchCV



def get_data():
    
    X,y=make_moons(n_samples=500, noise=0.2, random_state=666)

    return X,y

    '''
    plt.scatter(X[y==0,0],X[y==0,1],alpha=0.5)
    
    plt.scatter(X[y==1,0],X[y==1,1],alpha=0.5)
    
    plt.show()
    '''

def use_random_forest_std_clf(X,y):
    
    rb_clf=RandomForestClassifier(n_estimators=500,random_state=666,oob_score=True,n_jobs=-1,\
                                  boostrap=True,max_depth=3,max_leaf_nodes=9,min_samples_split=2,min_samples_leaf=6)
    
    
    
    ### ensemble to make 500 dct_tree(n_estimators)
    
    rb_clf.fit(X,y)
    
    print(rb_clf.oob_score_)
    
def use_grid_cv_search(X,y):
    
    grid_params=[
        
        {"n_estimators":[500],
         "random_state":[666],
         "max_depth":[i for i in range(2,6)],
         "min_samples_split":[i for i in range(2,21)],
         "min_samples_leaf":[i for i in range(1,11)],
         "max_leaf_nodes":[i for i in range(2,11)],
         "oob_score":['True']
            
            }
        ]
    
    rb_clf=RandomForestClassifier()
    
    grid_cv=GridSearchCV(rb_clf,grid_params)
    
    grid_cv.fit(X,y)
     
    return grid_cv.best_params_,grid_cv.best_estimator_.oob_score


def use_random_extra_tree_clf(X,y):
    
    rb_clf=ExtraTreesClassifier(n_estimators=500,random_state=666,oob_score=True,n_jobs=-1,\
                                 bootstrap=True, max_depth=3,max_leaf_nodes=9,min_samples_split=2,min_samples_leaf=6)
    
    
    rb_clf.fit(X,y)
    
    print(rb_clf.oob_score_)
    
def main():
    
    X,y=get_data()
    
    #use_random_forest_std_clf(X, y)
    
    use_random_extra_tree_clf(X, y)
    
    #params,score=use_grid_cv_search(X, y)
    
    #print(params,score)

if __name__ == '__main__':
    main()