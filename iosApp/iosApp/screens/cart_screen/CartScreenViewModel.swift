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
    @Published var price:Float = 0
    @Published var showMap = false
    let fee:Float = 1.0
    
   
    func fetchCoffee(){
        self.cartList = []
        UseCases().useGetCarts().execute(completionHandler: {res, err in
            
            let carts = res?.data as! [CartItem]
            
            
            carts.forEach{item in
                UseCases().useGetCoffeeDetailById().execute(id: item.productId, completionHandler: {res, err in
                    let coffee = res?.data
                    self.cartList.append(CoffeeCart(coffee: coffee, amount: Int(item.amount)))
                })
            }
            
        })
    }
    
    func countPrice(){
        self.price = 0
        cartList.forEach{item in
            price += (item.coffee?.price ?? 0) * Float(item.amount)
        }
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
        countPrice()
    }
    
    func switchShowingMap(){
        self.showMap = !self.showMap
    }
    
    
}
