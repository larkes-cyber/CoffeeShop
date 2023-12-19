//
//  IdentifiableCoffee.swift
//  iosApp
//
//  Created by MacBook on 15.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

struct IdentifiableCoffee:Identifiable{
    
    let itemId = UUID()
    let includeBeans:Bool
    let includeMilk:Bool
    let categoryId:String
    let categoryTitle:String
    let subtitle:String
    let description:String
    let totalScore:Float
    let scoreCount:Int
    let price:Float
    let id:String
    
}

extension Coffee{
    
    func toIndCoffee() -> IdentifiableCoffee{
        return IdentifiableCoffee(includeBeans: includeBeans, includeMilk: includeMilk, categoryId: categoryId, categoryTitle: categoryTitle, subtitle: subtitle, description: description, totalScore: totalScore, scoreCount: Int(scoreCount), price: price, id: id)
    }
    
}
