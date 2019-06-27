'''
Created on 2019年6月12日

@author: qiguangqin
'''

import numpy as np
from collections import Counter
from matplotlib.colors import ListedColormap
import matplotlib.pyplot as plt
from sklearn.datasets import load_iris,make_moons
from sklearn.model_selection import train_test_split
from math import log2
from decision_tree.dec_tree_repre import create_tree,predict
from sklearn.metrics import accuracy_score


np.random.seed(666)

def get_data():
    
    iris=load_iris()
    
    X=iris.data[iris.target<2,:2]
    
    y=iris.target[iris.target<2]
    
    return X,y

def get_data2():

    X,y=make_moons(n_samples=500, noise=0.2, random_state=666)

    return X,y

def get_train_test(X,y):
    
    X_train,X_test,y_train,y_test=train_test_split(X,y,random_state=66)
    
    return X_train,X_test,y_train,y_test

def plot_decision_boundary(tree,axis):  ##　绘制决策边界
    
    x0,x1=np.meshgrid(np.linspace(axis[0],axis[1],int((axis[1]-axis[0])*100)).reshape(-1,1),\
                      
                      np.linspace(axis[2],axis[3],int((axis[3]-axis[2])*100)).reshape(-1,1),\
                    ) 
    
    X_new=np.c_[x0.ravel(),x1.ravel()] ## 按照行进行合并矩阵，连个矩阵左右相加
    
    y_predict=np.array([ predict(x_new, tree) for x_new in X_new ])
    
    zz=y_predict.reshape(x0.shape)
    
    custom_cmap = ListedColormap(['#D3D3D3','#FFF59D','#B0C4DE'])
    
    plt.contourf(x0, x1, zz, cmap=custom_cmap) 


class Random_Forest_clf():
      
    def __init__(self,tree_num):
        
        self.tree_num=tree_num
        
        self.trees_result_=[]
        
        self.index_=[]
        
    
    def fit(self,X_train,y_train):
        
        def choose_sample(X,y,k):
            
            m,n=X.shape
    
            res_x=np.empty(shape=(m,n))
    
            res_y=np.empty(shape=m)
            
            for i in range(m):
    
                index_m=np.random.permutation(m)
        
                res_x[i]=X[index_m[i]]
        
                res_y[i]=y[index_m[i]]
        
            for j in range(k):
        
                index_n=np.random.permutation(n)
        
                res_x[:,j]=res_x[:,index_n[j]]
           
            return res_x[:,:k],res_y,index_n[:k]
        
          
        n=X_train.shape[1]
        
        k=(int(log2(n-1))+1 if n>2 else 1)
        
        for _ in range(self.tree_num):
        
            X_sample,y_sample,index_k=choose_sample(X_train, y_train, k)
            
            tree=create_tree(X_sample, y_sample)
            
            self.trees_result_.append(tree)
            
            self.index_.append(index_k)
            
        return self
    
       
    def predict(self,X_test):
        
        def get_test_split(X_predict,fea):

            X_test_sample=X_predict[:,fea]
    
            return X_test_sample
        
        
        m=len(X_test)
    
        m_tree=len(self.trees_result_)
    
        result=[]
    
        final_res=[]
    
        for i in range(m_tree):
        
            clf=self.trees_result_[i]
        
            fea=self.index_[i]
    
            X_test_sample=get_test_split(X_test,fea)
        
            sample_res= [ predict(x_test_sample, clf) for x_test_sample in X_test_sample]
        
        result.append(sample_res)
                 
        for j in range(m):
    
            final_res.append(Counter(list(np.array(result)[:,j])).most_common(1)[0][0])
        
        return np.array(final_res)
    
    
    def score(self,X_test,y_test):
    
        y_predict=self.predict(X_test)
        
        score=accuracy_score(y_test, y_predict)
        
        return score
    
    
'''
def random_forest(X,y,tree_num=50):
    
    n=X.shape[1]
    
    trees_result=[] ##save the each best split of random_forest
    
    index=[]
    
    k=(int(log2(n-1))+1 if n>2 else 1)
    
    for _ in range(tree_num):  ## tree_num equals to the tree_num which random_forest needed to train
        
        X_sample,y_sample,index_k=choose_sample(X, y, k)
        
        tree=create_tree(X_sample, y_sample)
        
        trees_result.append(tree)
        
        index.append(index_k)
        
    return trees_result,index

def choose_sample(X,y,k):
    
    m,n=X.shape
    
    res_x=np.empty(shape=(m,n))
    
    res_y=np.empty(shape=m)
    
    for i in range(m):
    
        index_m=np.random.permutation(m)
        
        res_x[i]=X[index_m[i]]
        
        res_y[i]=y[index_m[i]]
        
    for j in range(k):
        
        index_n=np.random.permutation(n)
        
        res_x[:,j]=res_x[:,index_n[j]]
           
    return res_x[:,:k],res_y,index_n[:k]



def get_predict(trees_result,index,X_test):
    
    m=len(X_test)
    
    m_tree=len(trees_result)
    
    result=[]
    
    final_res=[]
    
    for i in range(m_tree):
        
        clf=trees_result[i]
        
        fea=index[i]
    
        X_test_sample=get_test_split(X_test,fea)
        
        sample_res= [ predict(x_test_sample, clf) for x_test_sample in X_test_sample]
        
        result.append(sample_res)
            
    
    for j in range(m):
    
        final_res.append(Counter(list(np.array(result)[:,j])).most_common(1)[0][0])
        
    return np.array(final_res)


def get_test_split(X_predict,fea):
    

    X_test_sample=X_predict[:,fea]
    
    return X_test_sample
'''
   
   
   
def main():
    
    X,y=get_data2()
    
    tree_num=50
    
    rf_clf=Random_Forest_clf(tree_num)
    
    X_train,X_test,y_train,y_test=get_train_test(X, y)
    
    rf_clf.fit(X_train, y_train)
    
    y_pre=rf_clf.predict(X_test)
    
    score=rf_clf.score(X_test, y_test)
    
    print("y_predict",y_pre,"score",score)
    
    
    
    '''
    k=2
    
    X_sample,y_sample,index_k=choose_sample(X, y, k)
    
    print(X_sample[:10],y_sample[:10],index_k)    
    
    plt.scatter(X_sample[y_sample==0,0],X_sample[y_sample==0,1],alpha=0.5)
    
    plt.scatter(X_sample[y_sample==2,0],X_sample[y_sample==2,1],alpha=0.5,color="g")
    
    plt.scatter(X_sample[y_sample==1,0],X_sample[y_sample==1,1],alpha=0.5,color="r")
    
    plt.show()
    
    
    X_train,X_test,y_train,y_test=get_train_test(X, y)
    
    trees_result,index=random_forest(X_train, y_train)
    
    y_pre=get_predict(trees_result, index, X_test)
     
    print(y_pre,y_test,len(y_test))
    
    score=accuracy_score(y_test, y_pre)
    
    print(score)
   
    '''
    
if __name__ == '__main__':
    main()