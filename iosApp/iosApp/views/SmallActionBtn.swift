//
//  SmallActionBtn.swift
//  iosApp
//
//  Created by MacBook on 20.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct SmallActionBtn: View {
    
    let iconSystemName:String
    let callback:() -> Void
    
    var body: some View {
        Button(action: {
            callback()
        }, label: {
            ZStack{
                Color(hexStringToUIColor(hex: "C67C4E"))
                Image(systemName: iconSystemName)
                    .foregroundColor(.white)
            }
        })
        .frame(width: 32, height: 32)
        .cornerRadius(10)
    }
}


