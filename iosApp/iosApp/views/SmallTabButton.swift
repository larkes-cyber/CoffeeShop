//
//  SmallTabButton.swift
//  iosApp
//
//  Created by MacBook on 19.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct SmallTabButton: View {
    
    let text:String
    let isSelected:Bool
    let callback:() -> Void
    
    
    var body: some View {
        Button(action: {callback()}, label: {
            ZStack{
                Text(self.text)
                    .font(.system(size: 14, weight: .regular))
                    .foregroundColor(Color(hexStringToUIColor(hex: isSelected ? "C67C4E" : "2f2d2c")))
            }
            .frame(maxWidth: .infinity, minHeight: 43)
            .overlay(
                RoundedRectangle(cornerRadius: 12)
                    .stroke(Color(hexStringToUIColor(hex: self.isSelected ? "C67C4E" : "DEDEDE")), lineWidth: 1)
            )
        })
    }
}

