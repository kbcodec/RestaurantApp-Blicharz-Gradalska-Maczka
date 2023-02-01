package com.example.projecttest4;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.projecttest4.MyTimer;
import com.example.projecttest4.R;
import com.example.projecttest4.fragments.TimeFragment;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

public class TimeFragmentTest {
    private TimeFragment fragment;

    @Mock
    private LayoutInflater mockInflater;
    @Mock
    private ViewGroup mockContainer;
    @Mock
    private Bundle mockBundle;
    @Mock
    private View mockView;
    @Mock
    private TextView mockTimeView;
    @Mock
    private FloatingActionButton mockStartButton;
    @Mock
    private FloatingActionButton mockStopButton;
    @Mock
    private GoogleSignInAccount mockCurrentAccount;
    @Mock
    private GoogleSignIn mockGoogleSignIn;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        fragment = new TimeFragment();
        when(mockInflater.inflate(R.layout.fragment_time, mockContainer, false)).thenReturn(mockView);
        when(mockView.findViewById(R.id.timeView)).thenReturn(mockTimeView);
        when(mockView.findViewById(R.id.start)).thenReturn(mockStartButton);
        when(mockView.findViewById(R.id.stop)).thenReturn(mockStopButton);
        when(mockGoogleSignIn.getLastSignedInAccount(mockView.getContext())).thenReturn(mockCurrentAccount);
    }

    @Test
    public void testOnCreateView() {
        View view = fragment.onCreateView(mockInflater, mockContainer, mockBundle);
        verify(mockInflater).inflate(R.layout.fragment_time, mockContainer, false);
        assertEquals(mockView, view);
    }

    @Test
    public void testStartButtonClick() {
        fragment.onCreateView(mockInflater, mockContainer, mockBundle);
        View.OnClickListener startButtonListener = Mockito.spy(View.OnClickListener.class);
        startButtonListener.onClick(mockView);
        verify(MyTimer.getInstance()).startTimer(mockView);
    }
}
