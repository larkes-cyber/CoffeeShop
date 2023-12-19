//
//  IdentifiableCategory.swift
//  iosApp
//
//  Created by MacBook on 15.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct IdentifiableCategory:Identifiable{
    let itemId = UUID()
    var title: String
    var id:String
}

extension CoffeeCategory{
    
    func toIndCoffeeCategory() -> IdentifiableCategory{
        return IdentifiableCategory(title: title, id: id)
    }
    
}
