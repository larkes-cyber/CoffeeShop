//
//  FavoriteScreenViewModel.swift
//  iosApp
//
//  Created by MacBook on 20.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

class FavoriteScreenViewModel:ObservableObject{
    
    @Published var coffee:[IdentifiableCoffee] = []
    @Published var isTransferToDetailScreenActive = false
    @Published var selectedCoffeeToTranfer = ""
    
    init() {
        self.fetchCoffee()
    }
    
    func fetchCoffee(){
        UseCases().useGetFavoriteCoffee().execute(completionHandler: { res, err in
            let coffee = res?.data ?? []
            self.coffee = (coffee as! [Coffee]).map{$0.toIndCoffee()}
        })
    }
    
    func transferToDetail(id:String){
        self.isTransferToDetailScreenActive = true
        self.selectedCoffeeToTranfer = id
    }

    
}
