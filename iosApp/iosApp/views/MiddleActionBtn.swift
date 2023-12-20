//
//  MiddleActionBtn.swift
//  iosApp
//
//  Created by MacBook on 20.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct MiddleActionBtn: View {
    
    let iconName:String
    let title:String
    let callback:() -> Void
    
    var body: some View {
        HStack(alignment: .center){
            Image(self.iconName)
                .padding(.leading, 4)
            Text(self.title)
                .font(.system(size: 14, weight: .regular))
        }
        .padding(.horizontal, 12)
        .padding(.vertical, 6.5)
        .overlay(
            RoundedRectangle(cornerRadius: 16)
                .stroke(Color(hexStringToUIColor(hex: "DEDEDE")), lineWidth: 1)
        )
    }
}

