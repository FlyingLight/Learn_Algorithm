'''
Created on 2018年11月8日

@author: qiguangqin

'''

import numpy as np

import matplotlib.pyplot as plt

from test_pca_graident_asent.gradient_asent_test01 import get_X


class PCA:
    
    '''
    all pca could not use standScaler to std,because std variance 
    '''
    
    def __init__(self,n):
        
        self.n=n
        
        self.n_components_=None
        
       
    def fit(self,X,eta=0.01): 
        
        assert self.n<=X.shape[0],"n_components must be less than X first dimension"
        
        def demean(X):
            
            assert X.shape[1]>=2,"X must be 2 dimension Matrix"
            
            mean=np.mean(X,axis=0).reshape(-1,2)
            
            return X-mean
        
        
        def f(X,w):
            
            res=np.sum(X.dot(w)**2)
            
            return res/len(X)
        
        
        def df_math_ve(X,w):
            
            res=X.T.dot(X.dot(w))
            
            return res*2./len(X)
        
        def direction(w):
            
            return w/np.linalg.norm(w)
        
        def first_component(df,X,initial_w,eta,n_iters=1e4,epsilon=1e-8):
            
            w=initial_w
            
            w=direction(w)
            
            i_iter=0.0
            
            while i_iter<=n_iters:
                
                last_w=w
            
                gradient=df(X,w)
            
                w+=gradient*eta
                
                w=direction(w)
                
                if(abs(f(X,w)-f(X,last_w))<epsilon):
                    
                    break
                
                i_iter+=1
                
            return w   
        
        
        X_pca=demean(X)
        
        initial_w=np.random.random(X.shape[1])
        
        self.n_components_=np.empty((self.n,X_pca.shape[1]))
        
        for i in range(self.n):
            
            w=first_component(df_math_ve, X_pca, initial_w, eta)
            
            self.n_components_[i,:]=w
            
            '''
            for i in range(len(X_pca)):
            
            X_pca[i]=X_pca[i]-X_pca[i].dot(w)*w
            '''
            
            X_pca=X_pca-X_pca.dot(w).reshape(-1,1)*w
            
        return self
    
    
    def transform(self,X):
        
        '''
        X m*n matrix 
        
        X. Wk T 
        '''
        
        assert X.shape[1]==self.n_components_.shape[1],"X and n_components matrix must have same features"
        
        res=X.dot(self.n_components_.T)
        
        return res
    
    def inverse_transform(self,X):
        
        '''
        X m*k matrix
        components k*n
        
        original x --->   X.dot(components)
        '''
        
        assert X.shape[1]==self.n_components_.shape[0],"Xk and n_components matrix must have same features"
        
        res=X.dot(self.n_components_)
        
        return res
               
def main():
    
    X=get_X()
    
    eta=0.01
    
    pca=PCA(n=1)
    
    pca.fit(X, eta)
    
    X_reduction=pca.transform(X) 
    
    print(pca.n_components_,X_reduction.shape) 
    
    X_restore=pca.inverse_transform(X_reduction)
    
    plt.scatter(X[:,0],X[:,1],alpha=0.5)
    
    plt.scatter(X_restore[:,0],X_restore[:,1],color='r',alpha=0.5)
    
    plt.show()
    
        
if __name__ == '__main__':
    
    main()