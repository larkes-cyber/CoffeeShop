//
//  AppMap.swift
//  iosApp
//
//  Created by MacBook on 22.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import MapKit

struct Location: Identifiable {
    let id = UUID()
    let name: String
    let coordinate: CLLocationCoordinate2D
}

struct AppMap: View {
    
    let callback:() -> Void
    @State private var mapRegion = MKCoordinateRegion(center: CLLocationCoordinate2D(latitude: 51.5, longitude: -0.12), span: MKCoordinateSpan(latitudeDelta: 0.2, longitudeDelta: 0.2))

    @State private var tappedCoordinate: CLLocationCoordinate2D?
    var body: some View {
        Map(coordinateRegion: .constant(MKCoordinateRegion(
           center: CLLocationCoordinate2D(latitude: 37.7749, longitude: -122.4194), // Initial map center
           span: MKCoordinateSpan(latitudeDelta: 0.05, longitudeDelta: 0.05) // Initial zoom level
       )))
       .onTapGesture(count: 1, coordinateSpace: .global) { location in
               print("Tapped coordinate: \(location)")
               // Perform any additional actions with the tapped coordinate
       }
    }
}

