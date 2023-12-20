//
//  CoffeeDetailViewModel.swift
//  iosApp
//
//  Created by MacBook on 19.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import shared

class CoffeeDetailViewModel:ObservableObject{
    
    private var id:String? = nil
    
    @Published var coffee:Coffee? = nil
    @Published var selectedTitle:Int = 0
    @Published var isFavorite:Bool = false
    @Published var hasBeenAddedToCart:Bool = false
    
    
    func selectTitle(index:Int){
        selectedTitle = index
    }
    
    
    func getCoffee(id:String){
        UseCases().useGetCoffeeDetailById().execute(id: id, completionHandler: {res, err in
            let coffee = res?.data
            self.coffee = coffee
            UseCases().useCheckFavoriteCoffee().execute(coffeeId: coffee?.id ?? "", completionHandler: {res, err in
                self.isFavorite = res?.boolValue ?? false
            })
        })
    }
    
    func switchFavoriteCoffee(){
        if(isFavorite){
            UseCases().useRemoveFavoriteCoffee().execute(id: coffee?.id ?? "", completionHandler: {res, err in
                
            })
        }else{
            UseCases().useAddFavoriteCoffee().execute(id: coffee?.id ?? "", completionHandler: {res, err in
                
            })
        }
        isFavorite = !isFavorite
    }
    
    
    func switchCartBtnMode(){
        self.hasBeenAddedToCart = !self.hasBeenAddedToCart
        
        if(self.hasBeenAddedToCart){
            UseCases().useAddCart().execute(cart: CartItem(id: nil, amount: 1, productId: coffee?.id ?? ""), completionHandler: {res, err in
                
            })
        }else{
            UseCases().useDeleteCart().execute(productId: coffee?.id ?? "", completionHandler: {err in
                
            })
        }
    }
    
    

    
}
