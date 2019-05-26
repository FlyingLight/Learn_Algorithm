'''
Created on 2018年10月25日

@author: qiguangqin
'''

import numpy as np

import matplotlib.pyplot as plt

def get_X():
    
    np.random.seed(666)
    
    X=np.empty(shape=(100,2))
    
    X[:,0]=np.random.uniform(0.,100.,size=(100))
    
    X[:,1]=0.75*X[:,0]+3.+np.random.normal(0.,10.,size=100)
    
    return X

def demean(X):
    
    mean=np.mean(X,axis=0).reshape(-1,2)
    
    return X-mean   ## 对X 矩阵每一列进行，都减去平均值
    

def f(w,X):
    
    return np.sum(X.dot(w)**2)/len(X)
   
def df_ve(w,X):
    
    '''
    使用向量的方法，进行梯度的化简
    
    如果w 模不为1，那么eta 必须很小，循环次数必须非常多，性能就会下降
    '''
    
    m=len(X)
    
    res=X.T.dot(X.dot(w))
    
    return res*2.0/m

def df_math(w,X,epsilon=1e-8):
    
    res=np.empty(len(w))
    
    for i in range(len(w)):
        
        w1=w.copy()
        
        w2=w.copy()
        
        w1[i]+=epsilon
        
        w2[i]-=epsilon
        
        res[i]=(f(w1,X)-f(w2,X))/(2.*epsilon)
    
    return res

def df_w(w,X):
    
    res=np.empty(len(w))
    
    for i in range(len(w)):
        
        res[i]=X.dot(w).dot(X[:,i])
        
    return res*2.0/len(X)


def direction_w(w):
    
    '''
        使用 np.linalg.norm(w) 得到w 向量的模
    '''

    return w/np.linalg.norm(w)

def gradient_asent(df,X,initial_w,eta,n_iter=1e4,epsilon=1e-8):
    
    '''
    |w| must be 1，w必须是一个单位向量
    
    initial_w 变成一个单位向量
    '''
    
    w=direction_w(initial_w)
    
    i_iter=0.0
    
    while i_iter<=n_iter:
        
        last_w=w
        
        gradient=df(w,X)
        
        w+=gradient*eta
        
        w=direction_w(w)
        
        if(abs(f(w,X)-f(last_w,X))<epsilon):
        
            break
        
        i_iter+=1
        
    return w
    
def n_first_component(n,df,X,eta,n_iters=1e4,epsilon=1e-8):
    
    assert n<=X.shape[1],"n must be less than dimension of X"
    
    X_pca=demean(X.copy())
    
    res=[]
    
    initial_w=np.random.random(X.shape[1])
    
    for i in range(n):
    
        #initial_w=np.random.random(X.shape[1])
        
        w=gradient_asent(df, X_pca, initial_w, eta)
    
        res.append(w)
    
        X_pca=X_pca-X_pca.dot(w).reshape(-1,1) *w
        
    return np.array(res),i

   
def main():
    
    X=get_X()
    
    np.random.seed(10)
    
    eta=0.01
    
    w,i=n_first_component(2, df_ve, X,eta)
    
    print(w.shape,w,i)
    
    '''
    plt.scatter(X[:,0],X[:,1],alpha=0.5)
    
    plt.plot([0,w[0]*30],[0,w[1]*30],color='r')
    
    plt.show()
    '''
if __name__ == '__main__':
    
    main()