//
//  AppPrimaryButton.swift
//  iosApp
//
//  Created by MacBook on 11.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct AppPrimaryButton: View {
    
    var callback: () -> Void
    var title:String
    
    var body: some View {
        Button(action: {
            callback()
        }, label: {
            Text(title)
                .foregroundColor(Color(hexStringToUIColor(hex: "FFFFFF")))
                .frame(maxWidth: .infinity, maxHeight: 12)
                .padding(.vertical, 25)
        })
        .background(Color(hexStringToUIColor(hex: "C67C4E")))
        .foregroundColor(Color(hexStringToUIColor(hex: "FFFFFF")))
        .clipShape(RoundedRectangle(cornerRadius: 12.0))
        
        
    }
}
