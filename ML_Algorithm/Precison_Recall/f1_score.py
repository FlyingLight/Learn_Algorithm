'''
Created on 2019年2月5日

@author: qiguangqin
'''

import numpy as np

def f1_score_test(precision,recall):
    
    try:
       
        return 2.0*(precision*recall)/(precision+recall)
    
    except:
        
        return 0.0
    
    

    
def main():
    
    pass

if __name__ == '__main__':
    main()