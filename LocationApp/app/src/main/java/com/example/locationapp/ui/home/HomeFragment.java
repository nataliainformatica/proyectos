package com.example.locationapp.ui.home;

import static android.content.Context.LOCATION_SERVICE;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.locationapp.databinding.FragmentHomeBinding;

import java.util.List;

public class HomeFragment extends Fragment implements LocationListener {

    private FragmentHomeBinding binding;

    static final long TIEMPO_MIN = 10 * 1000; // 10 segundos
    static final long DISTANCIA_MIN = 5; // 5 metros
    static final String[] A = {"n/d", "preciso", "impreciso"};
    static final String[] P = {"n/d", "bajo", "medio", "alto"};
    static final String[] E = {"fuera de servicio",
            "temporalmente no disponible ", "disponible"};
    LocationManager manejador;
    String proveedor;
    TextView salida;

    private static final int PERMISSION_REQUEST_CODE = 123;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        salida = binding.salida;
        manejador = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);
        log("Proveedores de localización: \n ");
        muestraProveedores();

        if (ActivityCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            requestLocationPermission();

        } else {

            Location localizacion = manejador.getLastKnownLocation(proveedor);
            muestraLocaliz(localizacion);

    }

        return root;
    }


    private void requestLocationPermission() {
        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_REQUEST_CODE);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permiso concedido, puedes iniciar la obtención de la ubicación
                @SuppressLint("MissingPermission") Location localizacion = manejador.getLastKnownLocation(proveedor);
                muestraLocaliz(localizacion);
            } else {
                // Permiso denegado, puedes mostrar un mensaje al usuario o tomar alguna otra acción
            }
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    @Override
    public void onResume() {
        super.onResume();
      //  manejador.requestLocationUpdates(proveedor, TIEMPO_MIN, DISTANCIA_MIN,
      //          this);
    }

    @Override public void onPause() {
        super.onPause();
        manejador.removeUpdates(this);
    }

    // Métodos de la interfaz LocationListener
    @Override public void onLocationChanged(Location location) {
        log("Nueva localización: ");
        muestraLocaliz(location);
    }

    @Override public void onProviderDisabled(String proveedor) {
        log("Proveedor deshabilitado: " + proveedor + "\n");
    }

    @Override public void onProviderEnabled(String proveedor) {
        log("Proveedor habilitado: " + proveedor + "\n");
    }

    @Override public void onStatusChanged(String proveedor, int estado,
                                          Bundle extras) {
        log("Cambia estado proveedor: " + proveedor + ", estado="
                + E[Math.max(0, estado)] + ", extras=" + extras + "\n");
    }

    // Métodos para mostrar información
    private void log(String cadena) {
        salida.append(cadena + "\n");
    }

    private void muestraLocaliz(Location localizacion) {
        if (localizacion == null)
            log("Localización desconocida\n");
        else{
            log(localizacion.toString() + "\n");

            double latitude = localizacion.getLatitude();
            double longitude = localizacion.getLongitude();
            log(latitude + " " + longitude + "\n");
        }
    }

    private void muestraProveedores() {
        log("Proveedores de localización: \n ");
        List<String> proveedores = manejador.getAllProviders();
        proveedor = proveedores.get(0);
        for (String proveedor : proveedores) {
            muestraProveedor(proveedor);
        }
    }

    private void muestraProveedor(String proveedor) {
        LocationProvider info = manejador.getProvider(proveedor);
        log("LocationProvider[ " + "getName=" + info.getName()
                + ", isProviderEnabled="
                + manejador.isProviderEnabled(proveedor) + ", getAccuracy="
                + A[Math.max(0, info.getAccuracy())] + ", getPowerRequirement="
                + P[Math.max(0, info.getPowerRequirement())]
                + ", hasMonetaryCost=" + info.hasMonetaryCost()
                + ", requiresCell=" + info.requiresCell()
                + ", requiresNetwork=" + info.requiresNetwork()
                + ", requiresSatellite=" + info.requiresSatellite()
                + ", supportsAltitude=" + info.supportsAltitude()
                + ", supportsBearing=" + info.supportsBearing()
                + ", supportsSpeed=" + info.supportsSpeed() + " ]\n");
    }

}