//
//  MainScreenViewModel.swift
//  iosApp
//
//  Created by MacBook on 14.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import shared


class MainScreenViewModel:ObservableObject{
    
    private var oldCoffeeCards = [IdentifiableCoffee]()
    
    @Published var coffeeCategories = [IdentifiableCategory]()
    @Published var coffeeCards = [IdentifiableCoffee]()
    
    @Published var user:User? = nil
    
    @Published var isActive =  false
    
    @Published var activeCategory:String? = nil
    
    @Published var isCoffeeDetailSelected:Bool = false
    @Published var selectedCoffeeId:String? = nil
    
    @Published var isLoading = false
    
    @Published var imageId = UUID()
    
    init() {
        self.syncData()
    }
    
       

    
    func syncData(){
        UseCases().useFullAppSync().execute(completionHandler: {res, err in
            UseCases().useGetCoffeeCategories().execute(completionHandler: {res, err in
                if(res?.data != nil){
                    let categories = res?.data as! [CoffeeCategory]
                    self.coffeeCategories = categories.map({coffee in coffee.toIndCoffeeCategory()})
                    self.activeCategory = categories[0].id
                    self.refreashCoffee()
                }
                self.isLoading = false
            })
            UseCases().useGetUserData().execute(completionHandler: { res, err in
                self.user = res?.data
                self.imageId = UUID()
            })
        })
        
    }
    
    private func refreashCoffee(){
        UseCases().useGetCoffeeByCategory().execute(categoryId: activeCategory ?? "", completionHandler: {res, err in
            let coffee = res?.data as! [Coffee]
            self.coffeeCards = []
            self.oldCoffeeCards = []
            coffee.forEach{coffee in
                self.oldCoffeeCards.append(coffee.toIndCoffee())
                self.coffeeCards.append(coffee.toIndCoffee())
            }
        })
    }
    
    func changeCategory(id:String){
        activeCategory = id
        refreashCoffee()
    }
    
    func onFilter(str:String){
        print(str)
        if(str.isEmpty){
            refreashCoffee()
        }else{
            UseCases().useSearchForCoffee().execute(symbols: str, completionHandler: {res, err in
                let coffee = res?.data  as! [Coffee]
                self.coffeeCards = coffee.map({coffee in coffee.toIndCoffee()})
            })
        }
    }
    
    func selectCoffee(id:String){
        isCoffeeDetailSelected = true
        selectedCoffeeId = id
    }
    
    func addToCart(id:String){
        UseCases().useAddCart().execute(cart: CartItem(id: nil, amount: 1, productId: id), completionHandler: {res, err in
        })
    }
    

    
}
