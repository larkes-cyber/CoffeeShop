//
//  CategoryItem.swift
//  iosApp
//
//  Created by MacBook on 15.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct CategoryItem: View {
    
    let category:String
    let callback:() -> Void
    let isActive:Bool
    
    var body: some View {
        Button(action: {
            callback()
        }, label: {
            Text(category)
                .font(.system(size: 16, weight:.regular))
                .foregroundColor( isActive ? .white : Color(hexStringToUIColor(hex: "2F4B4E")))
                .padding()
        })
        .frame(maxHeight: 38)
        .background(isActive ? Color(hexStringToUIColor(hex: "C67C4E")) : .white)
        .clipShape(RoundedRectangle(cornerRadius: 12.0))
        
    }
}


