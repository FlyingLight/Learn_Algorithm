'''
Created on 2018年11月9日

@author: qiguangqin
'''

from sklearn.datasets import load_digits

from sklearn.model_selection import train_test_split

import numpy as np

from sklearn.decomposition import PCA

import matplotlib.pyplot as plt


def get_data():
    
    digits=load_digits()
    
    X=digits.data
    
    y=digits.target
    
    print(X.shape)
    
    return X,y

def get_de_noisy():
    
    X,y=get_data()
    
    noisy_digits=X+np.random.normal(0,4,size=X.shape)
    
    example_digits=noisy_digits[y==0,:][:10]
    
    for num in range(1,10):
        
        X_num=noisy_digits[y==num,:][:10] ## 每一num都有10行数据
        
        example_digits=np.vstack([example_digits,X_num])
        
            
    return example_digits


def plot_digits(data):
    
    fig,axes=plt.subplots(10,10,figsize=(10,10),subplot_kw={'xticks':[],'yticks':[]},gridspec_kw=dict(hspace=0.1,wspace=0.1))
    
    for i,ax in enumerate(axes.flat):
    
        ax.imshow(data[i].reshape(8,8),cmap='binary',interpolation='nearest',clim=(0,16))
        
    plt.show()
    

def pca_remove_noisy_repre():
    
    example_digits=get_de_noisy()
    
    pca=PCA(0.8)
    
    '''
    poly pca standscaler fit transform 类似
    '''
    
    pca.fit(example_digits) 
    
    print(pca.explained_variance_ratio_[:20],pca.components_.shape)
    
    components=pca.transform(example_digits)
    
    filtered_comp=pca.inverse_transform(components)
    
    plot_digits(filtered_comp)
    

if __name__ == '__main__':
    
    example_digits=get_de_noisy()
    
    plot_digits(example_digits)
    
    pca_remove_noisy_repre()