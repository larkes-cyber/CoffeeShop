//
//  OutlineButtonWithCheck.swift
//  iosApp
//
//  Created by MacBook on 20.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct OutlineButtonWithCheck: View {
    
    let title:String
    let callback:() -> Void
    
    
    var body: some View {
        Button(action: {
            callback()
        }, label: {
            HStack{
                Image(systemName: "checkmark")
                Text(title)
            }
        })
        .frame(height: 62)
        .frame(maxWidth: .infinity)

        .overlay(
            RoundedRectangle(cornerRadius: 16)
                .stroke(Color.accentColor, lineWidth: 1)
        )
    }
    
}
