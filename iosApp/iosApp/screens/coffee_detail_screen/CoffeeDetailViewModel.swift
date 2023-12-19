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
    
    
    func selectTitle(index:Int){
        selectedTitle = index
    }
    
    
    func getCoffee(id:String){
        UseCases().useGetCoffeeDetailById().execute(id: id, completionHandler: {res, err in
            let coffee = res?.data
            self.coffee = coffee
        })
    }
    
    
}
