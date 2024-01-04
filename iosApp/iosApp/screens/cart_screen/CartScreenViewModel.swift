//
//  CartScreenViewModel.swift
//  iosApp
//
//  Created by MacBook on 20.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared
import MapKit

class CartScreenViewModel:ObservableObject{
    
    @Published var cartList:[CoffeeCart] = []
    @Published var price:Float = 0
    @Published var showMap = false
    let fee:Float = 1.0
    @Published var deliveryMode = "Pick Up"
    @Published var selectedLocation:Location? = nil
    @Published var showingAlert = false
    

    
    let coords = [
        Location(name: "Buckingham Palace", coordinate: CLLocationCoordinate2D(latitude: 51.501, longitude: -0.141)),
        Location(name: "Tower of London", coordinate: CLLocationCoordinate2D(latitude: 51.508, longitude: -0.076))
    ]
    
   
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
    
    func onDeliveryModeChange(mode:String){
        self.deliveryMode = mode
    }
    
    func selectLocation(location:Location){
        self.selectedLocation = location
    }
    
    func switchAlertActivity(){
        showingAlert = !showingAlert
    }
    
}
