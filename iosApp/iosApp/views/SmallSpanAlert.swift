//
//  SmallSpanAlert.swift
//  iosApp
//
//  Created by MacBook on 04.01.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct SmallSpanAlert: View {
        
    let title:String
    let callback:() -> Void
    
    var body: some View {
        ZStack{
            Color(hexStringToUIColor(hex: "FFFFFF"))
            HStack(alignment: .center){
                Text(title)
                    .font(.system(size: 15))
                    .foregroundColor(Color(hexStringToUIColor(hex: "2F2D2C")))
                Spacer()
                Button(action: {
                    callback()
                }, label: {
                    Image(systemName: "xmark")
                        .foregroundColor(Color(hexStringToUIColor(hex: "2F2D2C")))
                })
            }
            .padding(.horizontal, 8)
        }
        .frame(maxWidth: .infinity)
        .frame(height: 52)
        .clipShape(RoundedCorner(radius: 10))
        .shadow(radius: 1)
    }
}
