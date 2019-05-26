'''
Created on 2018年11月26日

@author: qiguangqin

'''
import numpy as np

from sklearn.neighbors import KNeighborsClassifier

from test03.metrics import accuracy_score

from sklearn.model_selection import train_test_split

from sklearn.datasets import load_digits

#from sklearn.model_selection import cross_val_score ##使用cross_validation

from sklearn.model_selection import GridSearchCV




def get_data():
    
    digits=load_digits()
    
    X=digits.data
    
    y=digits.target
    
    return X,y

def cross_val_score(model,X_train,y_train,cv):

    assert len(X_train)==len(y_train)

    #assert len(X_train)>=cv

    m=len(X_train)

    shuffle_indexes=np.random.permutation(m)

    val_per_m=int(m/cv)

    scores=[]
    
    
    def get_train_val(X_train,y_train,train_indexes,val_indexes):
        
        X_train_real=X_train[train_indexes]

        y_train_real=y_train[train_indexes]

        X_train_val=X_train[val_indexes]

        y_train_val=y_train[val_indexes]
        
        return X_train_real,y_train_real, X_train_val, y_train_val

    for i in range(cv):

        val_indexes=shuffle_indexes[i*val_per_m:(i+1)*val_per_m]

        train_indexes=np.array([index for index in shuffle_indexes if index not in val_indexes])
        
        X_train_real,y_train_real, X_train_val, y_train_val=get_train_val(X_train, y_train, train_indexes, val_indexes)

        model.fit(X_train_real,y_train_real)
        
        #y_train_val_pre=model.predict(X_train_val)

        score=model.score(X_train_val,y_train_val)

        scores.append(score)


    return np.array(scores)



def get_train_test(X,y):
    
    X_train,X_test,y_train,y_test=train_test_split(X,y,test_size=0.2,random_state=666)
    
    return X_train,X_test,y_train,y_test


def grid_search_knn(X_train,y_train):
    
    param_grid=[  {"weights":['distance'],\
                   
                   "n_neighbors":[i for i in range(2,11)],\
                   
                   "p":[i for i in range(1,6)]\
        
        }  ]
    
    knn_reg=KNeighborsClassifier()

    grid_search=GridSearchCV(knn_reg,param_grid,verbose=1,cv=5) ##cv 把train 数据集合分成的份数
    
    grid_search.fit(X_train,y_train)
    
    print(grid_search.best_params_)
    
    

def get_cross_val(X_train,y_train):
    
    best_score,best_p,best_k=0,0,0
    
    for k in range(2,11):  ##使用网格搜索的方法来得到
        
        for p in range(1,6):
    
            knn_clf=KNeighborsClassifier()
    
            scores=cross_val_score(knn_clf,X_train,y_train,3) ##cv 把train 数据集合分成的份数
            
            ##传入X_train and y_train 就会自动进行 交叉验证的过程，返回每一个模型的准确率
            
            ## 使用交叉验证的方式得到三个score，AB C，分别作为测试和验证数据集合，得到模型的score，从而得到 k p 
            
            ##默认数据会分成三份，进行验证，得到score ，取得平均值
    
            score=np.mean(scores)
            
            if score>best_score:
                
                best_score,best_p,best_k=score,p,k
    
    return best_score,best_p,best_k
    '''
    默认是 分为三份，进行交叉验证，ABC 把测试数据集分为三份 
    使用随机的方式实现 ，train 和validate的分组
    '''

def knn_clf_best_score(X_train,y_train,X_test,y_test):
    
    best_score,best_p,best_k=0.0,0.0,0
    
    for k in range(2,11):
        
        for p in range(1,6):
            
            knn_clf=KNeighborsClassifier(weights="distance",n_neighbors=k,p=p)
            
            knn_clf.fit(X_train,y_train)
            
            score=knn_clf.score(X_test,y_test)
            
            if score>best_score:
                
                best_score,best_p,best_k=score,k,p
                
    return best_score,best_p,best_k


def main():
    
    X,y=get_data()
    
    X_train,X_test,y_train,y_test=get_train_test(X, y)
    
    #best_score,best_p,best_k=knn_clf_best_score(X_train, y_train, X_test, y_test)
    
    best_score,best_p,best_k=get_cross_val(X_train, y_train)
    
    print("best_score=",best_score,"best_p=",best_p,"best_k=",best_k)

    grid_search_knn(X_train, y_train)
    
if __name__ == '__main__':
    
    main()