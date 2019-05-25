#coding=utf-8
'''
Created on 2018��8��22��

@author: qiguangqin
'''

from sklearn.neighbors import KNeighborsClassifier

from sklearn.model_selection import train_test_split

from sklearn.datasets import load_digits

from sklearn.metrics import accuracy_score 

from sklearn.model_selection import GridSearchCV

'''
standard  sklearn method repr 

test_size reflect proportion of test sub-matrix: train sub_matrix

'''

def get_data():
    
    digits=load_digits()

    X=digits.data

    y=digits.target
    
    return X,y


def train_test(X,y):

    X_train,X_test,y_train,y_test=train_test_split(X,y,test_size=0.2,random_state=666)  
    
    return  X_train,X_test,y_train,y_test


def find_knn_score(X,y):

    X_train,X_test,y_train,y_test=train_test(X,y)
    
    knn_my_clf=KNeighborsClassifier(n_neighbors=20)
     
    knn_my_clf.fit(X_train,y_train) ##把knn 做一个拟合的过程，几乎每一个算法都需要fit

    ##y_predict=knn_classifier.predict(X_test)

    ##score= accuracy_score(y_test, y_predict) ## 调用函数 查看 算法的准确性 

    score1=knn_my_clf.score(X_test,y_test)

    return score1

'''
test_size 测试数据集 占据的 percent 
'''

def find_best_k(X,y):
    
    best_score=0.0
    
    best_k=-1
    
    best_method=""
    
    for k in range(1,50):
        
        for method in["uniform","distance"]: ## 考虑到与距离的倒数 ，作为权重，避免平票的问题
            
            ##　曼哈顿　距离p=1 1/p=1  欧拉距离 p=2 1/p=1/2   p 也是一个超参数
        
            knn_my_clf=KNeighborsClassifier(n_neighbors=k,weights=method)
        
            X_train,X_test,y_train,y_test=train_test(X, y)
        
            knn_my_clf.fit(X_train,y_train)
        
            score=knn_my_clf.score(X_test,y_test)
            
            if(score>=best_score):
            
                best_k=k
            
                best_score=score
                
                best_method=method
        
    
    return best_method,best_k,best_score

def grid_research_data(X,y):
    
    X_train,X_test,y_train,y_test=train_test(X,y)
    
    grid_params=[
        {
        'weights':['uniform'],  ##   grid 搜参数 weights  n_neighbors 
        'n_neighbors':[k for k in range(1,11)]
            },
        {
        'weights':['distance'],
        'n_neighbors':[k for k in range(1,11)],
        'p':[k for k in range(1,6)]
            }
        ]
     
    knn_my_clf=KNeighborsClassifier()
    
    grid_research=GridSearchCV(knn_my_clf,grid_params)
    
    grid_research.fit(X_train,y_train)
     
    return grid_research.best_params_
                    
def main():
    
    X,y=get_data()
    
    #X_train,X_test,y_train,y_test=train_test(X,y)
    
    #score=find_knn_score(X, y)
    
    best_params=grid_research_data(X, y)
    
    print(best_params)
    
    #best_method,best_k,best_score=find_best_k(X, y)  
    
    '''
         如果找到的best_k 的值 再range =49  就是在边界上 就需要扩大范围进行搜索
    '''
    
    #print("best_method",best_method,"best_k=",best_k,"best_score=",best_score)
                
if __name__=="__main__":

    main()