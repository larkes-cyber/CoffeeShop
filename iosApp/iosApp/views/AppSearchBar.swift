//
//  AppSearchBar.swift
//  iosApp
//
//  Created by MacBook on 14.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct AppSearchBar: View {
    
    @State var text = ""
    
    var body: some View {
        ZStack{
            Color(hexStringToUIColor(hex: "313131"))
        
            if(text.isEmpty)
            Text("Search coffee")
                .font(.system(size: 14, weight:.regular))
                .foregroundColor(Color(hexStringToUIColor(hex: "989898")))
                .padding(.leading, 40)
            
            HStack(spacing: 12){
                Image("magnifyingglass")
                    .foregroundColor(Color(hexStringToUIColor(hex: "FFFFFF")))
                    .frame(width: 20, height: 20)
            }
        }
        .frame(width: .infinity, height: 52)
        .clipShape(Rectangle())
        
    }
}

#Preview {
    AppSearchBar()
}
