'''
Created on 2019年3月26日

@author: qiguangqin
'''

import numpy as np

import matplotlib.pyplot as plt

def get_data():
    
    x=np.arange(-4,5,1)
    
    y=np.array((x>=-2) & (x<=2),dtype="int")
    
    return x,y


def gaussian(x,l):
    
    gamma=1.0
    
    return np.exp(-gamma*(x-l)**2)


def use_guassian(l1,l2):
    
    x,y=get_data()
    
    X_new=np.empty(shape=(len(x),2))
    
    for i,data in enumerate(x):  # get each data from the vector x, index---->i, data---->data
        
        X_new[i,0]=gaussian(data, l1)
        
        X_new[i,1]=gaussian(data, l2)
        
    
    
    plt.scatter(X_new[y==0,0],X_new[y==0,1],alpha=0.5)
    
    plt.scatter(X_new[y==1,0],X_new[y==1,1],alpha=0.5)
    
    plt.show()
    
    
    
def main():
    
    '''
    x,y=get_data()
    
   
    plt.scatter(x[y==0],[0]*len(x[y==0]),alpha=0.5)
    
    plt.scatter(x[y==1],[0]*len(x[y==1]),alpha=0.5)
    
    plt.show()

    '''
    
    l1,l2=-1,1
    
    use_guassian(l1, l2)
    
if __name__ == '__main__':
    main()