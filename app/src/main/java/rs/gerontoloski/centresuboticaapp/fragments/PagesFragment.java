package rs.gerontoloski.centresuboticaapp.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rs.gerontoloski.centresuboticaapp.App;
import rs.gerontoloski.centresuboticaapp.Constants;
import rs.gerontoloski.centresuboticaapp.R;
import rs.gerontoloski.centresuboticaapp.retrofit.POJO.media.Media;
import rs.gerontoloski.centresuboticaapp.retrofit.POJO.page.Page;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Grigoriy Frank
 */
public class PagesFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "PagesFragment";
    private static final String LANGUAGE = "language";
    private static final String DRAWER_MENU_ITEM_ID = "drawerMenuItemId";
    private static final String SHOW_EMAIL_SEND_BUTTON = "showEmailSendButton";

    private WebView content;
    private ProgressBar progressBar;
    private FloatingActionButton fab;

    private TextView noInternetConnection;
    private TextView somethingWrongWithServer;
    private Button tryAgainButton;

    private String buildingImageLink;
    private String headerTitle;
    private String copyrightTitle;

    private MyWebChromeClient mWebChromeClient = null;
    private View mCustomView;
    private FrameLayout mCustomViewContainer;
    private WebChromeClient.CustomViewCallback mCustomViewCallback;


    public PagesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param language              language for page
     * @param drawerMenuItemId      number for drawerMenuItemId
     * @param showSendEmailButton   show email send button or not
     * @return A new instance of fragment PagesFragment.
     */

    public static PagesFragment newInstance(int language, int drawerMenuItemId, boolean showSendEmailButton) {
        PagesFragment fragment = new PagesFragment();
        Bundle args = new Bundle();
        args.putInt(LANGUAGE, language);
        args.putInt(DRAWER_MENU_ITEM_ID, drawerMenuItemId);
        args.putBoolean(SHOW_EMAIL_SEND_BUTTON, showSendEmailButton);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {

            loadDataForHomePageAndOtherPages(getArguments().getInt(LANGUAGE), getArguments().getInt(DRAWER_MENU_ITEM_ID));

            setTitles(getArguments().getInt(LANGUAGE));

        }

    }

    /**
     * Loads data for page
     *
     * @param language              language for page
     * @param drawerMenuItemId      selected drawer menu item
     */

    private void loadDataForHomePageAndOtherPages(int language, int drawerMenuItemId) {

        switch (language) {

            case Constants.LANGUAGE_SERBIAN:

                if (drawerMenuItemId == Constants.HOME_MENU_ITEM_POSITION) {

                    HomePageResponse(Constants.HOME_PAGE_ID_SERBIAN_1, Constants.HOME_PAGE_ID_SERBIAN_2);

                } else if (drawerMenuItemId == Constants.ABOUT_US_MENU_ITEM_POSITION) {

                    pageResponse(Constants.ABOUT_US_PAGE_ID_SERBIAN);

                } else if (drawerMenuItemId == Constants.ACTIVITIES_MENU_ITEM_POSITION) {

                    pageResponse(Constants.ACTIVITIES_PAGE_ID_SERBIAN);

                } else if (drawerMenuItemId == Constants.SERVICES_MENU_ITEM_POSITION) {

                    pageResponse(Constants.SERVICES_PAGE_ID_SERBIAN);

                } else if (drawerMenuItemId == Constants.RESIDENTIAL_HOMES_MENU_ITEM_POSITION) {

                    pageResponse(Constants.RESIDENTIAL_HOMES_PAGE_ID_SERBIAN);

                } else if (drawerMenuItemId == Constants.CONTACT_MENU_ITEM_POSITION) {

                    pageResponse(Constants.CONTACT_PAGE_ID_SERBIAN);

                }

                break;

            case Constants.LANGUAGE_ENGLISH:

                if (drawerMenuItemId == Constants.HOME_MENU_ITEM_POSITION) {

                    HomePageResponse(Constants.HOME_PAGE_ID_ENGLISH_1, Constants.HOME_PAGE_ID_ENGLISH_2);

                } else if (drawerMenuItemId == Constants.ABOUT_US_MENU_ITEM_POSITION) {

                    pageResponse(Constants.ABOUT_US_PAGE_ID_ENGLISH);

                } else if (drawerMenuItemId == Constants.ACTIVITIES_MENU_ITEM_POSITION) {

                    pageResponse(Constants.ACTIVITIES_PAGE_ID_ENGLISH);

                } else if (drawerMenuItemId == Constants.SERVICES_MENU_ITEM_POSITION) {

                    pageResponse(Constants.SERVICES_PAGE_ID_ENGLISH);

                } else if (drawerMenuItemId == Constants.RESIDENTIAL_HOMES_MENU_ITEM_POSITION) {

                    pageResponse(Constants.RESIDENTIAL_HOMES_PAGE_ID_ENGLISH);

                } else if (drawerMenuItemId == Constants.CONTACT_MENU_ITEM_POSITION) {

                    pageResponse(Constants.CONTACT_PAGE_ID_ENGLISH);

                }

                break;

            case Constants.LANGUAGE_HUNGARIAN:

                if (drawerMenuItemId == Constants.HOME_MENU_ITEM_POSITION) {

                    HomePageResponse(Constants.HOME_PAGE_ID_HUNGARIAN_1, Constants.HOME_PAGE_ID_HUNGARIAN_2);

                } else if (drawerMenuItemId == Constants.ABOUT_US_MENU_ITEM_POSITION) {

                    pageResponse(Constants.ABOUT_US_PAGE_ID_HUNGARIAN);

                } else if (drawerMenuItemId == Constants.ACTIVITIES_MENU_ITEM_POSITION) {

                    pageResponse(Constants.ACTIVITIES_PAGE_ID_HUNGARIAN);

                } else if (drawerMenuItemId == Constants.SERVICES_MENU_ITEM_POSITION) {

                    pageResponse(Constants.SERVICES_PAGE_ID_HUNGARIAN);

                } else if (drawerMenuItemId == Constants.RESIDENTIAL_HOMES_MENU_ITEM_POSITION) {

                    pageResponse(Constants.RESIDENTIAL_HOMES_PAGE_ID_HUNGARIAN);

                } else if (drawerMenuItemId == Constants.CONTACT_MENU_ITEM_POSITION) {

                    pageResponse(Constants.CONTACT_PAGE_ID_HUNGARIAN);

                }

                break;

            case Constants.LANGUAGE_ROMANA:

                if (drawerMenuItemId == Constants.HOME_MENU_ITEM_POSITION) {

                    HomePageResponse(Constants.HOME_PAGE_ID_ROMANA_1, Constants.HOME_PAGE_ID_ROMANA_2);

                } else if (drawerMenuItemId == Constants.ABOUT_US_MENU_ITEM_POSITION) {

                    pageResponse(Constants.ABOUT_US_PAGE_ID_ROMANA);

                } else if (drawerMenuItemId == Constants.ACTIVITIES_MENU_ITEM_POSITION) {

                    pageResponse(Constants.ACTIVITIES_PAGE_ID_ROMANA);

                } else if (drawerMenuItemId == Constants.SERVICES_MENU_ITEM_POSITION) {

                    pageResponse(Constants.SERVICES_PAGE_ID_ROMANA);

                } else if (drawerMenuItemId == Constants.RESIDENTIAL_HOMES_MENU_ITEM_POSITION) {

                    pageResponse(Constants.RESIDENTIAL_HOMES_PAGE_ID_ROMANA);

                } else if (drawerMenuItemId == Constants.CONTACT_MENU_ITEM_POSITION) {

                    pageResponse(Constants.CONTACT_PAGE_ID_ROMANA);

                }

                break;

            default:
                Toast.makeText(getActivity(), "Undefined language", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

       View rootView = inflater.inflate(R.layout.page_fragment, container, false);
        noInternetConnection = rootView.findViewById(R.id.no_internet_page_fragment);
        noInternetConnection.setVisibility(View.GONE);

        somethingWrongWithServer = rootView.findViewById(R.id.server_down_page_fragment);
        somethingWrongWithServer.setVisibility(View.GONE);

        mWebChromeClient = new MyWebChromeClient();
        content = rootView.findViewById(R.id.web_view_page_fragment);
        content.getSettings().setJavaScriptEnabled(true);
        content.setWebChromeClient(mWebChromeClient);

        progressBar = rootView.findViewById(R.id.progress_circular_page_fragment);
        progressBar.setVisibility(View.VISIBLE);

        tryAgainButton = rootView.findViewById(R.id.try_again_button_page_fragment);
        tryAgainButton.setOnClickListener(this);
        tryAgainButton.setVisibility(View.GONE);

        fab = rootView.findViewById(R.id.fab);
        // copyrightTextView = rootView.findViewById(R.id.text_copyright);


        if (getArguments() != null) {

            if (getArguments().getBoolean(SHOW_EMAIL_SEND_BUTTON)) {

                fab.setVisibility(View.VISIBLE);
                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String[] address = {"gerontoc@gcsu.rs"};

                        Intent intent = new Intent(Intent.ACTION_SENDTO);
                        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
                        intent.putExtra(Intent.EXTRA_EMAIL, address);
                        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                            startActivity(intent);
                        }

                    }
                });
            } else {

                fab.setVisibility(View.GONE);
            }


        }

        return rootView;
    }


    /**
     * For page response
     *
     * @param pageId           pageId. Take a look to Constants.java
     */
    private void pageResponse(int pageId) {

        App.getApi().getPageById(pageId).enqueue(new Callback<Page>() {
                                                     @Override
                                                     public void onResponse(@NonNull Call<Page> call, @NonNull Response<Page> response) {

                                                         if (response.isSuccessful() && response.body() != null) {


                                                             StringBuilder contentString = new StringBuilder();

                                                             contentString.append("<style>img{height: auto;max-width: 100%;}");
                                                             contentString.append(".wp-block-image {max-width: 100%;margin-bottom: 1em;margin-left: 0;margin-right: 0;}");
                                                             contentString.append(".alignleft {display: inline;float: left;margin-right: 1.5em;}" +
                                                                     ".alignright {display: inline;float: right;margin-left: 1.5em;}" +
                                                                     ".aligncenter {clear: both;display: block;margin-left: auto;margin-right: auto;}" +
                                                                     ".elementor-button {background-color: #6699cc;border: none; border-radius: 4px; color: white;" +
                                                                     "padding: 15px 32px;text-align: center;text-decoration: none;display: inline-block;font-size: 14px;text-decoration: none;display: inline-block;font-size: 14px;font-family: \"Raleway\", sans-serif;text-transform: uppercase;}" +
                                                                     ".center {display: block;margin-left: auto;margin-right: auto;}" +
                                                                     "</style>");
                                                             contentString.append("<h1 style=\"color: #69c;text-transform: uppercase;font-size: 20px;text-align: center; padding-left: 15%; padding-right: 15%;\"><div><img class=\"center\" src=\"https://gerontoloski.rs/wp-content/uploads/2019/02/loading.gif\"></div><a>");
                                                             contentString.append(headerTitle);
                                                             contentString.append("</a></h1>");
                                                             contentString.append("<p style=\"font-size: 32px; text-align: center\">");
                                                             contentString.append(response.body().getTitle().getRendered());
                                                             contentString.append("</p>");
                                                             contentString.append(response.body().getContent().getRendered());
                                                             contentString.append("<p style=\"font-size: 14px; text-align: center\">");
                                                             contentString.append(copyrightTitle);
                                                             contentString.append("</p>");

                                                             content.loadDataWithBaseURL(null,
                                                                     contentString.toString(),
                                                                     "text/html", "utf-8", null);

                                                             progressBar.setVisibility(View.GONE);
                                                             content.setVisibility(View.VISIBLE);


                                                         } else {

                                                             new Handler().postDelayed(new Runnable() {
                                                                 @Override
                                                                 public void run() {
                                                                     progressBar.setVisibility(View.GONE);
                                                                     content.setVisibility(View.GONE);
                                                                     somethingWrongWithServer.setVisibility(View.VISIBLE);
                                                                     tryAgainButton.setVisibility(View.VISIBLE);
                                                                 }
                                                             }, 1000);
                                                             //Toast.makeText(getActivity(), String.format("Response is %s.", String.valueOf(response.code())), Toast.LENGTH_LONG).show();
                                                         }

                                                     }

                                                     @Override
                                                     public void onFailure(@NonNull Call<Page> call, @NonNull Throwable t) {

                                                         new Handler().postDelayed(new Runnable() {
                                                             @Override
                                                             public void run() {
                                                                 progressBar.setVisibility(View.GONE);
                                                                 content.setVisibility(View.GONE);
                                                                 noInternetConnection.setVisibility(View.VISIBLE);
                                                                 tryAgainButton.setVisibility(View.VISIBLE);
                                                             }
                                                         }, 1000);


                                                         //Toast.makeText(getActivity(), "An error occurred during networking " + t.toString(), Toast.LENGTH_SHORT).show();
                                                         Log.e("TestRequestResults", "onFailure(): " + t.toString());

                                                     }
                                                 }

        );
    }

    /**
     * For "Home" page response. "Home" page consists of two pages
     *
     * @param firstPageId
     * @param secondPageId
     */

    private void HomePageResponse(int firstPageId, int secondPageId) {

        App.getApi().getTwoPagesByIds(firstPageId, secondPageId).enqueue(new Callback<List<Page>>() {
            @Override
            public void onResponse(Call<List<Page>> call, Response<List<Page>> response) {

                if (response.isSuccessful() && response.body() != null) {


                    List<Page> pages = new ArrayList<>(response.body());

                    StringBuilder contentString = new StringBuilder();//margin-left: 0;margin-right: 0;".fluid-width-video-wrapper iframe, .fluid-width-video-wrapper object, .fluid-width-video-wrapper embed {position: absolute;top: 0;left: 0;width: 100%;height: 100%;}" +

                    contentString.append("<style>img{height: auto;max-width: 100%;}");
                    contentString.append(".wp-block-embed {margin-bottom: 1em;}");
                    contentString.append("figure {display: block; margin-block-start: 1em;margin-block-end: 1em; margin-inline-start: 40px;margin-inline-end: 40px; text-align: center;}");
                    contentString.append("iframe {height: auto; max-width: 100%;margin-bottom: 1em; margin-left: 0px;margin-right: 0px;}");
                    contentString.append(".wp-block-image {max-width: 100%;margin-bottom: 1em;margin-left: 0;margin-right: 0;}");
                    contentString.append(".alignleft {display: inline;float: left;margin-right: 1.5em;}" +
                            ".alignright {display: inline;float: right;margin-left: 1.5em;}" +
                            ".aligncenter {clear: both;display: block;margin-left: auto;margin-right: auto;}" +
                            ".elementor-button {background-color: #6699cc;border: none; border-radius: 4px; color: white;padding: 15px 32px;text-align: center; text-decoration: none;display: inline-block;font-size: 14px;font-family: \"Raleway\", sans-serif;text-transform: uppercase;}" +
                            ".center {display: block;margin-left: auto;margin-right: auto;}" +
                            "</style>");
                    contentString.append("<h1 style=\"color: #69c;text-transform: uppercase;font-size: 20px;text-align: center; padding-left: 15%; padding-right: 15%;\"><div><img class=\"center\" src=\"https://gerontoloski.rs/wp-content/uploads/2019/02/loading.gif\"></div><a>");
                    contentString.append(headerTitle);
                    contentString.append("</a></h1>");
                    contentString.append("<img src=");
                    contentString.append("\"");
                    contentString.append("file:///android_res/drawable/building.jpg");
                    contentString.append("\"");
                    contentString.append("alt>");

                    Log.i("PagesFragment", "buildingImageLink = " + buildingImageLink);

                    for (Page page : pages) {

                        contentString.append("<p style=\"font-size: 32px; text-align: center\">");
                        contentString.append(page.getTitle().getRendered());
                        contentString.append("</p>");
                        contentString.append(page.getContent().getRendered());
                    }

                    contentString.append("<p style=\"font-size: 14px; text-align: center\">");
                    contentString.append(copyrightTitle);
                    contentString.append("</p>");


                    content.loadDataWithBaseURL(null,
                            contentString.toString(),
                            "text/html", "utf-8", null);

                    progressBar.setVisibility(View.GONE);

                    content.setVisibility(View.VISIBLE);


                } else {

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setVisibility(View.GONE);
                            content.setVisibility(View.GONE);
                            fab.setVisibility(View.GONE);
                            somethingWrongWithServer.setVisibility(View.VISIBLE);
                            tryAgainButton.setVisibility(View.VISIBLE);
                        }
                    }, 1000);
                    //Toast.makeText(getActivity(), String.format("Response is %s.", String.valueOf(response.code())), Toast.LENGTH_LONG).show();
                }


            }

            @Override
            public void onFailure(Call<List<Page>> call, Throwable t) {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setVisibility(View.GONE);
                        content.setVisibility(View.GONE);
                        fab.setVisibility(View.GONE);
                        noInternetConnection.setVisibility(View.VISIBLE);
                        tryAgainButton.setVisibility(View.VISIBLE);
                    }
                }, 1000);


                //Toast.makeText(getActivity(), "An error occurred during networking " + t.toString(), Toast.LENGTH_SHORT).show();
                Log.e("TestRequestResults", "onFailure(): " + t.toString());

            }
        });


    }


    /**
     * Set titles for texts in html page
     *
     * @param language
     */
    private void setTitles(int language) {

        switch (language) {

            case Constants.LANGUAGE_SERBIAN:

                headerTitle = getResources().getString(R.string.logo_title_serbian);
                copyrightTitle = getResources().getString(R.string.copyright_serbian);
                break;
            case Constants.LANGUAGE_ENGLISH:
                headerTitle = getResources().getString(R.string.logo_title_english);
                copyrightTitle = getResources().getString(R.string.copyright_english);
                break;
            case Constants.LANGUAGE_HUNGARIAN:
                headerTitle = getResources().getString(R.string.logo_title_hungarian);
                copyrightTitle = getResources().getString(R.string.copyright_hungarian);
                break;
            case Constants.LANGUAGE_ROMANA:
                headerTitle = getResources().getString(R.string.logo_title_romana);
                copyrightTitle = getResources().getString(R.string.copyright_romana);
                break;
            default:
                break;
        }
    }


    /**
     * for "Try Again" button
     *
     * @param v view
     */
    @Override
    public void onClick(View v) {

        progressBar.setVisibility(View.VISIBLE);
        noInternetConnection.setVisibility(View.GONE);
        somethingWrongWithServer.setVisibility(View.GONE);
        tryAgainButton.setVisibility(View.GONE);

        if (getArguments() != null) {

            loadDataForHomePageAndOtherPages(getArguments().getInt(LANGUAGE), getArguments().getInt(DRAWER_MENU_ITEM_ID));

            if (getArguments().getBoolean(SHOW_EMAIL_SEND_BUTTON)) {

                fab.setVisibility(View.VISIBLE);
            }

        }


    }


    private class MyWebChromeClient extends WebChromeClient {
        FrameLayout.LayoutParams LayoutParameters = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT);

        @Override
        public void onShowCustomView(View view, CustomViewCallback callback) {

            // if a view already exists then immediately terminate the new one
            if (mCustomView != null) {
                callback.onCustomViewHidden();
                return;
            }

            mCustomViewContainer = getView().findViewById(R.id.video_container);
            mCustomViewContainer.addView(view);
            mCustomView = view;
            mCustomViewCallback = callback;
            mCustomViewContainer.setVisibility(View.VISIBLE);

        }

        @SuppressWarnings("UnnecessaryReturnStatement")
        @Override
        public void onHideCustomView() {

            super.onHideCustomView();
            if (mCustomView == null) {
                return;
            } else {

                mCustomView.setVisibility(View.GONE);
                mCustomViewContainer.removeView(mCustomView);
                mCustomView = null;
                mCustomViewContainer.setVisibility(View.GONE);
                mCustomViewCallback.onCustomViewHidden();
            }
        }
    }

}

