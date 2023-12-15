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
    
    init() {
        refreash()
    }
    
       
    func refreash(){
        UseCases().useGetCoffeeCategories().execute(completionHandler: {res, err in
            if(res?.data != nil){
                let categories = res?.data as! [CoffeeCategory]
                self.coffeeCategories = categories.map({coffee in coffee.toIndCoffeeCategory()})
                self.activeCategory = categories[0].id
                self.refreashCoffee()
            }
        })
        
        
        UseCases().useGetUserData().execute(completionHandler: { res, err in
            self.user = res?.data
        })
        
    }
    
    func refreashCoffee(){
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
    
}
