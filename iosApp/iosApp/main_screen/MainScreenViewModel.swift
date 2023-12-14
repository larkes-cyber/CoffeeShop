//
//  MainScreenViewModel.swift
//  iosApp
//
//  Created by MacBook on 14.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import shared


class MainScreenViewModel:ObservableObject{
    
    private var oldCoffeeCards = [Coffee]()
    
    @Published var coffeeCategories = [CoffeeCategory]()
    @Published var coffeeCards = [Coffee]()
    
    @Published var user:User? = nil
    
    @Published var isActive =  false
    
    init() {
        refreash()
    }
    
       
    func refreash(){
        UseCases().useGetCoffeeCategories().execute(completionHandler: {res, err in
            if(res?.data != nil){
                let categories = res?.data as! [CoffeeCategory]
                self.coffeeCategories = categories
                categories.forEach{category in
                    UseCases().useGetCoffeeByCategory().execute(categoryId: category.id, completionHandler: {res, err in
                        let coffee = res?.data as! [Coffee]
                        coffee.forEach{coffee in
                            self.oldCoffeeCards.append(coffee)
                            self.coffeeCards.append(coffee)
                        }
                    })
                }
            }
        })
        
        UseCases().useGetUserData().execute(completionHandler: { res, err in
            self.user = res?.data
        })
        
    }
    
    func onFilter(str:String){
        
    }
    
}
