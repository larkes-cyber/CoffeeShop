//
//  CircularProgressBar.swift
//  iosApp
//
//  Created by MacBook on 11.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct CircularProgressBar: View {
    @State private var progress: CGFloat = 0.0
       let timer = Timer.publish(every: 0.003, on: .main, in: .common).autoconnect()

       var body: some View {
           ZStack {
               Circle()
                   .stroke(Color.gray.opacity(0.2), style: StrokeStyle(lineWidth: 7, lineCap: .round))
                   .frame(width: 50, height: 50)

               Circle()
                   .trim(from: 0, to: progress)
                   .stroke(Color(hexStringToUIColor(hex: "C67C4E")), style: StrokeStyle(lineWidth: 7, lineCap: .round))
                   .frame(width: 50, height: 50)
                   .rotationEffect(.degrees(-90))

               Text("\(Int(progress * 100))%")
                   .font(.system(size: 14, weight:.bold))
                   .foregroundColor(Color(hexStringToUIColor(hex: "C67C4E")))
           }
           .onReceive(timer) { _ in
               if progress < 1.0 {
                   progress += 0.01
               }
           }
       }
}


