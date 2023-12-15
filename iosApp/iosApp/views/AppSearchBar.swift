//
//  AppSearchBar.swift
//  iosApp
//
//  Created by MacBook on 14.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct AppSearchBar: View {
    
    
    let callback:(String) -> Void
    
    @State var text = ""
    
    var body: some View {
        ZStack(alignment: .leading){
            Color(hexStringToUIColor(hex: "313131"))
        
            HStack{
                if(text.isEmpty){
                    Text("Search coffee")
                        .font(.system(size: 14, weight:.regular))
                        .foregroundColor(Color(hexStringToUIColor(hex: "989898")))
                        .padding(.leading, 40)
                }
                Spacer()
            }
            
            Image(systemName: "magnifyingglass")
                .foregroundColor(Color(hexStringToUIColor(hex: "FFFFFF")))
                .frame(width: 20, height: 20)
                .padding(.leading, 15)
            
            TextField("",text: $text)
                .foregroundColor(Color(hexStringToUIColor(hex: "DDDDDD")))
                .padding(.leading, 40)
                .onChange(of: text){value in
                    callback(text)
                }

            
        }
        .frame(height: 56)
        .clipShape(Rectangle())
        .clipShape(RoundedRectangle(cornerRadius: 16.0))
        
    }
}


