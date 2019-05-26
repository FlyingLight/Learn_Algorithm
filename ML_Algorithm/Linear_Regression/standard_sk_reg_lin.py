'''
Created on 2018年9月25日

@author: qiguangqin
'''

import  numpy as np

from sklearn.linear_model import LinearRegression

from sklearn.model_selection import train_test_split

from sklearn.datasets import load_boston

from sklearn.neighbors import KNeighborsRegressor

#from sklearn.grid_search import GridSearchCV


def get_data():
    
    boston=load_boston()
    
    X=boston.data
    
    y=boston.target
    
    X=X[y<50]
    
    y=y[y<50]
    
    return X,y

def get_train_test(X,y):
    
    X_train,X_test,y_train,y_test=train_test_split(X,y,test_size=0.2,random_state=666)
    
    return X_train,X_test,y_train,y_test


def repre_lin_reg(X,y):
    
    
    X_train,X_test,y_train,y_test=get_train_test(X,y)
    
    lin_reg=LinearRegression()
    
    lin_reg.fit(X_train,y_train)
    
    score=lin_reg.score(X_test,y_test)
    
    cof_sort=np.argsort(lin_reg.coef_)  
    
    interception=lin_reg.intercept_
    
    '''
    
    lin_reg.coef_ 查看系数  
    
    lin_reg.intercept_ 查看截距
    '''
    
    return score,cof_sort,interception
   
def repre_knn_reg(X,y):
    
    X_train,X_test,y_train,y_test=get_train_test(X,y)
    
    knn_lin_reg=KNeighborsRegressor()
    
    knn_lin_reg.fit(X_train,y_train)
    
    score=knn_lin_reg.score(X_test,y_test)
   
    print(score)

def repre_knn_grid_search(X,y): 
    
    X_train,X_test,y_train,y_test=get_train_test(X,y)
   
    params_grid=[  
        
       {
        "weights":["uniform"],
        "n_neighbors": [i for i in range(1,11)]
           },
       {
        "weights":["distance"],
        "n_neighbors":[i for i in range(1,11)],
        "p":[p for p in range(1,6)]
        
        }
            ] 
       
       
    knn_reg_grid=KNeighborsRegressor()
    
    # grid_search=GridSearchCV(knn_reg_grid,params_grid,n_jobs=-1)
    
    # grid_search.fit(X_train,y_train)
    
    # best_params=grid_search.best_params_
    
    # return best_params

def main():
    
    X,y=get_data()
    
    score,coef,interception=repre_lin_reg(X, y)
    
    repre_knn_reg(X, y)
    
    print("coef=",coef,"score=",score,"interception=",interception)
    
    #best_params=repre_knn_grid_search(X,y)
    
    #print("score=",best_params)
    

if __name__ == '__main__':
    main()