//
//  AppMap.swift
//  iosApp
//
//  Created by MacBook on 22.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import MapKit



struct AppMap: View {
    
    var coords:[Location] = []
    var callback:(Location) -> Void
    
    
    
    @State private var mapRegion = MKCoordinateRegion(center: CLLocationCoordinate2D(latitude: 51.5, longitude: -0.12), span: MKCoordinateSpan(latitudeDelta: 0.2, longitudeDelta: 0.2))

    @State private var tappedCoordinate: CLLocationCoordinate2D?
    var body: some View {
        Map(coordinateRegion: $mapRegion, annotationItems: self.coords) { location in
            MapAnnotation(coordinate: location.coordinate) {
                Circle()
                    .stroke(.red, lineWidth: 3)
                    .frame(width: 44, height: 44)
                    .onTapGesture {
                        callback(location)
                    }
            }
        }
    }
}


struct Location: Identifiable {
    let id = UUID()
    let name: String
    let coordinate: CLLocationCoordinate2D
}
