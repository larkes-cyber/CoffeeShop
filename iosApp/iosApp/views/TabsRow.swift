//
//  TabsRow.swift
//  iosApp
//
//  Created by MacBook on 20.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct TabsRow: View {
    
    
    let titles:[String]
    let selectedTitle:String
    let callback:(String) -> Void
    
    var body: some View {
        ZStack{
            Color(hexStringToUIColor(hex: "F2F2F2"))
            HStack{
                ForEach(titles,id: \.self){item in
                    Button(action: {
                        callback(item)
                    }, label: {
                        ZStack{
                            if(item == selectedTitle){
                                Color.accentColor
                            }
                            Text(item)
                                .font(.system(size: 16, weight: .regular))
                                .foregroundColor(item == selectedTitle ? Color.white : Color(hexStringToUIColor(hex: "2f2d2c")))
                        }
                    })
                    .frame(maxWidth: .infinity)
                    .frame(height: 48)
                    .cornerRadius(10)
                }
            }
            .padding(4)
        }
        .cornerRadius(10)
    }
    
}


