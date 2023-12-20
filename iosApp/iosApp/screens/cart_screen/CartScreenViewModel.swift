//
//  CartScreenViewModel.swift
//  iosApp
//
//  Created by MacBook on 20.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

class CartScreenViewModel:ObservableObject{
    
    @Published var cartList:[CoffeeCart] = []
    
   
    func fetchCoffee(){
        self.cartList = []
        UseCases().useGetCarts().execute(completionHandler: {res, err in
            
            let carts = res?.data as! [CartItem]
            
            print(carts)
            
            carts.forEach{item in
                UseCases().useGetCoffeeDetailById().execute(id: item.productId, completionHandler: {res, err in
                    let coffee = res?.data
                    self.cartList.append(CoffeeCart(coffee: coffee, amount: Int(item.amount)))
                })
            }
            
        })
    }
    
    func changeAmount(index:Int, amount:Int){
        var currentCart = cartList[index]
        let currentCartAmount = currentCart.amount + amount
        if(currentCartAmount > 0){
            currentCart.amount = currentCartAmount
            cartList[index] = currentCart
        }else{
            cartList.remove(at: index)
        }
        UseCases().useChangeCartAmount().execute(id: currentCart.coffee?.id ?? "", amount: Int32(amount), completionHandler: {err in
            
        })
    }
    
    
}
