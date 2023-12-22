//
//  AppMap.swift
//  iosApp
//
//  Created by MacBook on 22.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct AppMap: View {
    
    let callback:() -> Void
    
    var body: some View {
        ZStack{
            Color.gray
            Button(action: {
                callback()
            }, label: {
                Text("click")
            })
        }
    }
}
