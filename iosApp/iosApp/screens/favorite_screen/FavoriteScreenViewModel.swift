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
    
    private var oldCoffee:[IdentifiableCoffee] = []
    
    @Published var coffee:[IdentifiableCoffee] = []
    @Published var isTransferToDetailScreenActive = false
    @Published var selectedCoffeeToTranfer = ""
    
    init() {
        self.fetchCoffee()
    }
    
    func syncData(){
        UseCases().useFullAppSync().execute(completionHandler: { res, err in
            self.fetchCoffee()
        })
    }
    
    private func fetchCoffee(){
        UseCases().useGetFavoriteCoffee().execute(completionHandler: { res, err in
            let coffee = res?.data ?? []
            self.coffee = (coffee as! [Coffee]).map{$0.toIndCoffee()}
            self.oldCoffee = self.coffee
        })
    }
    
    func transferToDetail(id:String){
        self.isTransferToDetailScreenActive = true
        self.selectedCoffeeToTranfer = id
    }

    func onSearch(text:String){
        if(text.isEmpty){
            self.coffee = oldCoffee
            return
        }
        self.coffee = oldCoffee.filter{$0.categoryTitle.lowercased().range(of:text.lowercased()) != nil || $0.subtitle.lowercased().range(of:text.lowercased()) != nil}
    }
    
    func addToCart(id:String){
        UseCases().useAddCart().execute(cart: CartItem(id: nil, amount: 1, productId: id), completionHandler: {res, err in
        })
    }
    
    
}
