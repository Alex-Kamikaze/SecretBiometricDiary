package com.example.secretdiary.ui;

import static androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG;
import static androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.secretdiary.R;
import com.example.secretdiary.databinding.FragmentAuthorizationBinding;

import java.util.concurrent.Executor;

public class AuthorizationFragment extends Fragment {
    FragmentAuthorizationBinding binding;
    BiometricManager manager;
    BiometricPrompt.PromptInfo promptInfo;
    BiometricPrompt authPrompt;

    public static AuthorizationFragment newInstance() {
        return new AuthorizationFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setDescription("Приложите палец к сканеру, чтобы подтвердить личность")
                .setTitle("Авторизация")
                .setAllowedAuthenticators(BIOMETRIC_STRONG | DEVICE_CREDENTIAL)
                .build();
        manager = BiometricManager.from(requireActivity());
        Executor mainExecutor = ContextCompat.getMainExecutor(requireActivity());
        authPrompt = new BiometricPrompt(requireActivity(), mainExecutor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                Toast.makeText(requireActivity().getApplicationContext(), "Ошибка: не удалось авторизоваться!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_authorizationFragment_to_diaryFragment);
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Toast.makeText(requireActivity().getApplicationContext(), "Не удалось авторизоваться!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAuthorizationBinding.inflate(inflater);
        binding.authButton.setOnClickListener(v -> {
            authPrompt.authenticate(promptInfo);
        });
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}